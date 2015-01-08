package org.dreamer.examination.repository;

import org.dreamer.examination.entity.Answer;
import org.dreamer.examination.vo.AnswerJudgeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lcheng on 2014/4/13.
 */
public interface AnswerDao extends JpaRepository<Answer,Long>{

    @Modifying
    @Query(value = "delete from Answer where examId = ?1 and quesId = ?2")
    public void deleteByExamIdAndQuesId(long examId,long quesId);

    @Modifying
    @Query(value = "delete from Answer where examId in (select id from Examination where schedule.id =?1)")
    public void deleteBySchedule(long scheduleId);


//    public void updateAnswer(Answer a);

    public int countByExamIdAndQuesId(long examId,long quesId);

    @Query("select new org.dreamer.examination.vo.AnswerJudgeVO(a.quesId,q.answer,a.answer as realAnswer,pq.score,pq.quesType) " +
            "from Examination e, Answer a,Question q,PaperQuestion pq " +
            "where a.examId =?1 and a.examId = e.id and e.paper.id = pq.paper.id and  a.quesId = q.id and a.quesId = pq.quesId")
    public List<AnswerJudgeVO> findCommitAndRealAnswer(long examId);

//    @Query("select a.quesId,q.answer,a.answer as realAnswer from Examination e,Answer a,Question q,PaperQuestion pq " +
//            "where a.examId =?1 and a.examId = e.id and e.paper.id = pq.paper.id and  a.quesId = q.id and a.quesId = pq.quesId")
//    public List<Object[]> findCommitAndRealAnswerArr(Long examId);

    @Deprecated
    @Query(value = "select v.quesId,v.answer,v.stuAnswer from v_exam_question_answers v where v.examId = ?1",nativeQuery = true)
    public List<Object[]> findCommitAndRealAnswerArr(Long examId);

    @Query(value = "select pq.quesId,q.answer,a.answer from examinations e,paper_questions pq,questions q,answers a where " +
            "e.id = ?1 and e.id = a.examId AND e.paper_id = pq.paper_id and pq.quesId = q.id and pq.quesId = a.quesId",nativeQuery = true)
    public List<Object[]> findCommitAndRealAnswerNoView(Long examId);

    @Query(value = "select v.quesType,v.typeQuestCount,v.correctCount from v_exam_answer_stats v where v.examId = ?1",nativeQuery = true)
    public List<Object[]> findExamAnswerStat(Long examId);

    @Modifying
    @Query("update Answer set answer=:answer where examId=:examId and quesId=:quesId")
    public void updateAnswer(@Param("examId")Long examId,@Param("quesId")Long quesId,@Param("answer")String answer);

    public List<Answer> findByExamId(Long examId);
}
