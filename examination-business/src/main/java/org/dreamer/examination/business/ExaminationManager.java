package org.dreamer.examination.business;

import org.dreamer.examination.entity.*;
import org.dreamer.examination.service.ExamTemplateService;
import org.dreamer.examination.service.ExaminationService;
import org.dreamer.examination.service.PaperService;
import org.dreamer.examination.service.QuestionService;
import org.dreamer.examination.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    private PaperService paperService;
    @Autowired
    @Qualifier("examPaperSaveExecutor")
    private AsyncTaskExecutor taskExecutor;
    @Autowired
    private CacheManager cacheManager;

    public Paper generatePaper(long tempId) {

        ExamTemplate template = templateService.getExamTemplate(tempId);
        if (null == template)
            return null;

        RandomStrategy randomStrategy = new DefaultQuestionRandomStrategy(questionService);

        List<MustChooseQuestionDef> mustChoose = template.getMustChooseDefs();
        List<TemplateQuestionDef> tempQuesDef = template.getQuestionDefs();

        Map<Types.QuestionType,List<PaperQuestionVO>> generated = randomStrategy.randomGenerate(tempQuesDef);
        if (mustChoose != null && mustChoose.size() > 0){
            for (MustChooseQuestionDef def :mustChoose){
                Types.QuestionType type = def.getQuestionType();
                if (generated.get(type) == null) {
                    generated.put(type,new ArrayList<PaperQuestionVO>());
                }
                generated.get(type).add(new PaperQuestionVO(def.getQuestionId(),def.getQuesScore()));
            }
        }

        Paper p = new Paper();
        p.setTemplate(template);
        p.setCreateTime(new Date());
        p.setPaperQuestions(generated);
        return p;
    }

    public List<FacadeQuestionVO> newExamination(long tempId,String staffId,Types.QuestionType type){
        Paper paper = generatePaper(tempId);
        Examination exam = new Examination();
        exam.setPaper(paper);
        exam.setExamStaffId(staffId);

        Callable<Long[]> task = new ExamPaperInfoSaveTask(examService,exam);
        Future<Long[]> ids = taskExecutor.submit(task);

        Map<Types.QuestionType,List<PaperQuestionVO>> quesIds  = paper.getPaperQuestions();
        List<PaperQuestionVO> typeIds = quesIds.get(type);
        List<FacadeQuestionVO> result = new ArrayList<>();
        for (PaperQuestionVO vo:typeIds){
            if (vo.getId()!=null){
               result.add(new FacadeQuestionVO(questionService.getQuestion(vo.getId()),vo.getScore()));
            }
            if (vo.getIds()!=null){
                for (Long id : vo.getIds()){
                    result.add(new FacadeQuestionVO(questionService.getQuestion(id),vo.getScore()));
                }
            }
        }

        Cache cache = cacheManager.getCache(Constants.CACHE_PAPER_QUES_ID);
        try {
            Long[] savedId = ids.get();
            cache.put(savedId[1],paper.getPaperQuestions());
        } catch (InterruptedException|ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<FacadeQuestionVO> getPaperQuestions(long paperId,Types.QuestionType questionType){
        Cache cache = cacheManager.getCache(Constants.CACHE_PAPER_QUES_ID);
        Map<Types.QuestionType,List<PaperQuestionVO>> cached = (Map<Types.QuestionType,List<PaperQuestionVO>>)cache.get(paperId);
        if (cached==null){
            //paperService
        }

        return null;
    }
}
