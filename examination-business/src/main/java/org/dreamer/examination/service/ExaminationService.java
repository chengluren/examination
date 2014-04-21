package org.dreamer.examination.service;

import com.google.common.collect.Sets;
import org.dreamer.examination.entity.*;
import org.dreamer.examination.repository.AnswerDao;
import org.dreamer.examination.repository.ExaminationDao;
import org.dreamer.examination.repository.PaperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public float scoreExam(long examId){
        List<AnswerJudgeVO> ajvos =  answerDao.findCommitAndRealAnswer(examId);
        if (ajvos!=null && ajvos.size()>0){
            float score = 0;
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
            return score;
        }
        return 0;
    }
}
