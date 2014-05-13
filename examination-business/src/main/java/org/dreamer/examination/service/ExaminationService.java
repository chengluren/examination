package org.dreamer.examination.service;

import com.google.common.collect.Sets;
import org.dreamer.examination.entity.*;
import org.dreamer.examination.repository.AnswerDao;
import org.dreamer.examination.repository.ExaminationDao;
import org.dreamer.examination.repository.PaperDao;
import org.dreamer.examination.repository.PaperQuestionDao;
import org.dreamer.examination.vo.AnswerJudgeVO;
import org.dreamer.examination.vo.ExamRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Service
public class ExaminationService {

    @Autowired
    private ExaminationDao examDao;
    @Autowired
    private PaperDao paperDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private PaperQuestionDao paperQuestionDao;

    public void addExamination(Examination examination){
        Paper p = examination.getPaper();
        paperDao.save(p);
        examination.setPaper(p);
        examDao.save(examination);
    }

    public Examination getExamination(long examId){
        return examDao.findOne(examId);
    }

    public Page<ExamRecordVO> getExamRecords(String straffId,Pageable page){
       return examDao.findStaffExamRecords(straffId,page);
    }

    public List<ExamRecordVO> getExamRecords(String straffId,Long scheduleId){
        return examDao.findStaffExamRecords(straffId,scheduleId);
    }
    @Deprecated
    public int getExamPaperQuestionCount(long examId,long paperId){
        return paperQuestionDao.countByExamIdAndPaperId(examId,paperId);
    }

    public Set<Types.QuestionType> getExamQuestionDistinctType(Long examId){
         return examDao.findExamQuestionDistinctType(examId);
    }

    public int getExamPaperQuestionCount(Long examId){
        return  examDao.countExamPaperQuestion(examId);
    }

    public List<Object[]> getExamQuestionScores(Long examId,Types.QuestionType questionType){
        return examDao.findExamQuestionScores(examId,questionType);
    }

    public List<Object[]> getExamQuestionAnswers(Long examId,Types.QuestionType questionType){
        return examDao.findExamQuestionAnswer(examId,questionType);
    }

    public float scoreExam(long examId){
        List<AnswerJudgeVO> ajvos =  answerDao.findCommitAndRealAnswer(examId);
        float score = 0;
        if (ajvos!=null && ajvos.size()>0){
            Types.QuestionType type = null;
            for (AnswerJudgeVO vo : ajvos){
                type = vo.getQuestionType();
                if (type.equals(Types.QuestionType.Choice) || type.equals(Types.QuestionType.TrueFalse)){
                    if (vo.getAnswer().trim().equalsIgnoreCase(vo.getRealAnswer())){
                        score += vo.getScore();
                    }
                }else if (type.equals(Types.QuestionType.MultipleChoice)){
                    String[] as = vo.getAnswer().trim().split(",");
                    String[] ras = vo.getRealAnswer().split(",");
                    Set<String> aset = Sets.newHashSet(as);
                    Set<String> rset = Sets.newHashSet(ras);
                    if (rset.size()==aset.size() && Sets.intersection(aset,rset).containsAll(aset)){
                        score +=vo.getScore();
                    }else if(rset.size()<aset.size() && Sets.intersection(aset,rset).containsAll(rset)){
                        score += (vo.getScore()/2.0);
                    }
                }
            }
        }
        examDao.updateExamFinalScore(examId,score,new Date());
        return score;
    }
}
