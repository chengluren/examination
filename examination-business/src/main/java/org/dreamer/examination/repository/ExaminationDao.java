package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamRecordVO;
import org.dreamer.examination.entity.Examination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface ExaminationDao extends CommonRepository<Examination,Long>{

    @Query("select new org.dreamer.examination.entity.ExamRecordVO(" +
            "e.examStaffId,e.schedule.id,e.schedule.name,e.finalScore,e.examStartTime)  " +
            "from Examination e where e.examStaffId = ?1 ")
    public Page<ExamRecordVO> findStaffExamRecords(String staffId,Pageable page);

    @Query("select new org.dreamer.examination.entity.ExamRecordVO(" +
            "e.examStaffId,e.schedule.id,e.schedule.name,e.finalScore,e.examStartTime)  " +
            "from Examination e where e.examStaffId = ?1 and e.schedule.id = ?2 ")
    public List<ExamRecordVO> findStaffExamRecords(String staffId,Long scheduleId);

    @Query("select new org.dreamer.examination.entity.ExamRecordVO(" +
            "e.examStaffId,e.schedule.id,e.schedule.name,e.finalScore,e.examStartTime)  " +
            "from Examination e where e.schedule.id = ?1 ")
    public Page<ExamRecordVO> findScheduleExamRecords(Long scheduleID ,Pageable page);

    @Query("select new org.dreamer.examination.entity.ExamRecordVO(" +
            "e.examStaffId,e.schedule.id,e.schedule.name,e.finalScore,e.examStartTime)  " +
            "from Examination e ")
    public Page<ExamRecordVO> findAllExamRecords(Pageable page);

    @Modifying
    @Query("update Examination set finalScore =:finalScore where id =:examId")
    public void updateExamFinalScore(@Param("examId")Long examId,@Param("finalScore")float finalScore);
}
