package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamSchedule;
import org.dreamer.examination.entity.Types;
import org.dreamer.examination.vo.ExamScheduleVO;
import org.dreamer.examination.vo.ScheduleDateVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface ExamScheduleDao extends JpaRepository<ExamSchedule,Long>{

    public Page<ExamSchedule> findByNameLike(String name,Pageable pageable);

    public int countByTemplateId(Long tempId);

//    @Query("select s.template.id from ExamSchedule s where s.major = (:major) and  current_time() " +
//            "between s.startDate and s.endDate order by s.startDate desc")
//    public List<Long> findScheduleByDate(@Param("major")String major,Pageable p);

//    @Query("from ExamSchedule s where s.major = (:major) and  current_time() " +
//            "between s.startDate and s.endDate order by s.startDate desc")
//    public List<ExamSchedule> findScheduleByDate(@Param("major")String major);

    @Query("select new org.dreamer.examination.vo.ExamScheduleVO(s.name,s.startDate,s.endDate,s.id,s.examTimeSpan) " +
            "from ExamSchedule s,ScheduleMajor sm where s.id = sm.scheduleId and current_time() < s.endDate " +
            "and sm.majorId = ?1 and s.admissionYear = ?2 and s.degree = ?3")
    public List<ExamScheduleVO> findSchedule(String major,int admissionYear,Types.DegreeType degree);


    @Query("select new org.dreamer.examination.vo.ScheduleDateVO( s.id , s.name,s.startDate,s.endDate  ) " +
            "from ExamSchedule s where s.startDate >= (:beginDate)  and s.endDate >= (:endDate) " +
            " or s.startDate <= (:beginDate) and  s.endDate >= (:endDate) or s.endDate >= (:beginDate) and  s.endDate <= (:endDate) "
            )
    public List<ScheduleDateVO> findScheduleByDateFilter(@Param("beginDate") Date beginDate ,@Param("endDate") Date endDate );

    @Query(value = "select distinct (s.stu_session) from jiaoda_member_student s order by s.stu_session limit 100",nativeQuery = true)
    public List<Integer> findStudentSession();

    @Modifying
    @Query("update ExamSchedule set majorNames =:majorNames where id = :id")
    public void updateScheduleMajorNames(@Param("id")Long id,@Param("majorNames")String majorNames);
}
