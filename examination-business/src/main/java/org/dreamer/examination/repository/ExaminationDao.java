package org.dreamer.examination.repository;

import org.dreamer.examination.entity.Types;
import org.dreamer.examination.vo.ExamRecordVO;
import org.dreamer.examination.entity.Examination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface ExaminationDao extends CommonRepository<Examination,Long>{

    @Query(value = "select count(pq.id) from Examination e,PaperQuestion pq where e.id = :examId and e.paper.id= pq.paper.id")
    public int countExamPaperQuestion(@Param("examId")Long examId);

    @Query("select new org.dreamer.examination.vo.ExamRecordVO(" +
            "e.examStaffId,e.id,e.schedule.id,e.schedule.name,e.finalScore,e.examStartTime)  " +
            "from Examination e where e.examStaffId = ?1 ")
    public Page<ExamRecordVO> findStaffExamRecords(String staffId,Pageable page);

    @Query("select new org.dreamer.examination.vo.ExamRecordVO(" +
            "e.examStaffId,e.id,e.schedule.id,e.schedule.name,e.finalScore,e.examStartTime)  " +
            "from Examination e where e.examStaffId = ?1 and e.schedule.id = ?2 ")
    public List<ExamRecordVO> findStaffExamRecords(String staffId,Long scheduleId);

    @Query("select new org.dreamer.examination.vo.ExamRecordVO(" +
            "e.examStaffId,e.id,e.schedule.id,e.schedule.name,e.finalScore,e.examStartTime)  " +
            "from Examination e where e.schedule.id = ?1 ")
    public Page<ExamRecordVO> findScheduleExamRecords(Long scheduleID ,Pageable page);

    @Query("select new org.dreamer.examination.vo.ExamRecordVO(" +
            "e.examStaffId,e.id,e.schedule.id,e.schedule.name,e.finalScore,e.examStartTime)  " +
            "from Examination e ")
    public Page<ExamRecordVO> findAllExamRecords(Pageable page);

    @Query("select distinct(pq.quesType) from Examination e,PaperQuestion pq where e.paper.id = pq.paper.id and e.examId =:examId")
    public Set<Types.QuestionType> findExamQuestionDistinctType(@Param("examId")Long examId);

    @Query("select pq.quesId,pq.score from Examination e,PaperQuestion pq where e.paper.id = pq.paper.id " +
            "and e.examId =:examId and pq.quesType =:quesType")
    public List<Object[]> findExamQuestionScores(@Param("examId")Long examId,@Param("quesType")Types.QuestionType quesType);

    @Query("select a.quesId,a.answer from Examination e,PaperQuestion pq,Answer a where e.id =:examId and e.paper.id = pq.paper.id " +
            "and pq.quesType=:quesType amd pq.quesId = a.quesId")
    public List<Object[]> findExamQuestionAnswer(@Param("examId")Long examId,@Param("quesType")Types.QuestionType quesType);

    @Modifying
    @Query("update Examination set finalScore =:finalScore,commitTime = :commitTime where id =:examId")
    public void updateExamFinalScore(@Param("examId")Long examId,@Param("finalScore")float finalScore,@Param("commitTime")Date commitTime);
}
