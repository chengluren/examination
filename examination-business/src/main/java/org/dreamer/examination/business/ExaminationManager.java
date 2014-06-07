package org.dreamer.examination.business;

import org.dreamer.examination.entity.*;
import org.dreamer.examination.service.*;
import org.dreamer.examination.utils.Constants;
import org.dreamer.examination.vo.ExamAndQuestionVO;
import org.dreamer.examination.vo.ExamQuestionVO;
import org.dreamer.examination.vo.PaperQuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Component
public class ExaminationManager {

    @Autowired
    private ExamTemplateService templateService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExaminationService examService;
    @Autowired
    private ExamScheduleService scheduleService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private PaperService paperService;
    @Autowired
    @Qualifier("examPaperSaveExecutor")
    private AsyncTaskExecutor taskExecutor;
    @Autowired
    private CacheManager cacheManager;

    /**
     * 生成试卷
     * @param tempId
     * @return
     */
    public Paper generatePaper(long tempId) {

        ExamTemplate template = templateService.getExamTemplate(tempId);
        if (null == template)
            return null;

        TemplateRandomStrategy randomStrategy = new DefaultTemplateRandomStrategy(questionService);

        Set<MustChooseQuestionDef> mustChoose = template.getMustChooseDefs();
        Set<TemplateQuestionDef> tempQuesDef = template.getQuestionDefs();

        List<TemplateQuestionDef> tempQuesDefList = new ArrayList<>(tempQuesDef);
        Map<Types.QuestionType,List<PaperQuestionVO>> generated = randomStrategy.randomGenerate(tempQuesDefList);
        if (mustChoose != null && mustChoose.size() > 0){
            for (MustChooseQuestionDef def :mustChoose){
                Types.QuestionType type = def.getQuestionType();
                if (generated.get(type) == null) {
                    generated.put(type,new ArrayList<PaperQuestionVO>());
                }
                generated.get(type).add(new PaperQuestionVO(def.getQuestionId(),def.getQuesScore()));
            }
        }

        boolean multiMixed = template.isMultiChoiceMixedInChoice();
        if(multiMixed && generated.containsKey(Types.QuestionType.MultipleChoice)){
            if (generated.containsKey(Types.QuestionType.Choice)){
                generated.get(Types.QuestionType.Choice).addAll(
                        generated.get(Types.QuestionType.MultipleChoice));
                generated.remove(Types.QuestionType.MultipleChoice);
            }
        }

        Paper p = new Paper();
        p.setTemplate(template);
        p.setCreateTime(new Date());
        p.setTypedQuestions(generated);
//        p.setPaperQuestions(generated);
        return p;
    }

    /**
     * 学生参加考试
     * @param staffId
     * @param major
     * @return
     */
    public ExamAndQuestionVO newExamination(String staffId,String major,long scheduleId){
        //Long tempId = scheduleService.getExamTemplateId(major);
        ExamSchedule examSchedule = scheduleService.getExamSchedule(scheduleId);
        long tempId = examSchedule.getTemplate().getId();
        ExamAndQuestionVO vo = new ExamAndQuestionVO();
        List<String> quesTypes = templateService.getDistinctQuesTypes(tempId);
        if (quesTypes==null|| quesTypes.size()==0) {
            return null;
        }
        vo.setQuesTypes(quesTypes);
        vo.setStartTime(examSchedule.getStartDate());
        vo.setEndTime(examSchedule.getEndDate());

        Paper paper = generatePaper(tempId);
        Examination exam = new Examination();
        exam.setPaper(paper);
        exam.setExamStaffId(staffId);
        exam.setExamStartTime(new Date());
        exam.setSchedule(examSchedule);

        Callable<Long[]> task = new ExamPaperInfoSaveTask(examService,paperService,exam);
        Future<Long[]> ids = taskExecutor.submit(task);

        Map<Types.QuestionType,List<PaperQuestionVO>> quesIds  = paper.getTypedQuestions();
        Types.QuestionType defaultType = Types.QuestionType.getTypeFromShortName(quesTypes.get(0));
        List<PaperQuestionVO> typeIds = quesIds.get(defaultType);
        List<ExamQuestionVO> questions =loadQuestions(typeIds);

        Cache cache = getCache();
        try {
            Long[] savedId = ids.get();
            cache.put(savedId[0],paper.getTypedQuestions());
            vo.setExamId(savedId[0]);
            vo.setPaperId(savedId[1]);
            //int totalCount = examService.getExamPaperQuestionCount(savedId[0],savedId[1]);
            int totalCount = examService.getExamPaperQuestionCount(savedId[0]);
            vo.setQuesTotalCount(totalCount);
        } catch (InterruptedException|ExecutionException e) {
            e.printStackTrace();
        }
        vo.setQuestions(questions);
        return vo;
    }

    /**
     * 加载某个人参与考试的某类型的题目
     * @param examId
     * @param questionType
     * @return
     */
    public List<ExamQuestionVO> getPaperQuestions(long examId,Types.QuestionType questionType){
        Cache cache = cacheManager.getCache(Constants.CACHE_PAPER_QUES_ID);
        Map<Types.QuestionType,List<PaperQuestionVO>> cached =null;
        if (cache.get(examId)!=null){
            Object cacheTarget = cache.get(examId).get();
            cached = (Map<Types.QuestionType,List<PaperQuestionVO>>)cacheTarget;
        }else{
            Examination exam = examService.getExamination(examId);
            if (exam!=null){
                Paper p = exam.getPaper();
                List<PaperQuestion> pqs = paperService.getPaperQuestions(p.getId());
                p.setPaperQuestions(pqs);
                p.toTypedQuestions();
                cached = p.getTypedQuestions();
                getCache().put(examId,cached);
            }else {
                return null;
            }
        }

        List<PaperQuestionVO> typeIds = cached.get(questionType);
        List<ExamQuestionVO> result =loadQuestions(typeIds);
        return result;
    }

    public void commitAnswers(List<Answer> answers){
        answerService.addAnswers(answers);
    }

    //====================private methods =================================================
    private List<ExamQuestionVO> loadQuestions(List<PaperQuestionVO> typeIds){
        List<ExamQuestionVO> result = new ArrayList<>();
        for (PaperQuestionVO vo:typeIds){
            if (vo.getId()!=null){
                Question q = questionService.getQuestion(vo.getId());
                ExamQuestionVO eqvo  = new ExamQuestionVO(q.getId(),q.getStem(),q.getImgPath(),vo.getScore());
                if (q instanceof ChoiceQuestion ||q instanceof MultipleChoiceQuestion){
                    eqvo.setOptions(((ChoiceQuestion)q).getQuestionOptions());
                }
                result.add(eqvo);
            }
            if (vo.getIds()!=null){
                for (Long id : vo.getIds()){
                    Question q = questionService.getQuestion(id);
                    ExamQuestionVO eqvo  = new ExamQuestionVO(q.getId(),q.getStem(),q.getImgPath(),vo.getScore());
                    if (q instanceof ChoiceQuestion ||q instanceof MultipleChoiceQuestion){
                        eqvo.setOptions(((ChoiceQuestion)q).getQuestionOptions());
                    }
                    result.add(eqvo);
//                    result.add(new ExamQuestionVO(q.getId(),q.getStem(),q.getImgPath(),vo.getScore()));
                }
            }
        }
        return result;
    }

    private Cache getCache(){
       return cacheManager.getCache(Constants.CACHE_PAPER_QUES_ID);
    }


}
