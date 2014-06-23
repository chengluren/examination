package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamSchedule;
import org.dreamer.examination.entity.ExamScheduleViewVO;
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

    //去掉时间限制
    @Query("select new org.dreamer.examination.vo.ExamScheduleVO(s.name,s.startDate,s.endDate,s.id,s.examTimeSpan) " +
            "from ExamSchedule s,ScheduleMajor sm where s.id = sm.scheduleId " +
            "and sm.majorId = ?1 and s.admissionYear = ?2 and s.degree = ?3 order by s.id desc ")
    public List<ExamScheduleVO> findSchedule(String major,int admissionYear,Types.DegreeType degree);

    @Query("select new org.dreamer.examination.vo.ScheduleDateVO( s.id , s.name,s.startDate,s.endDate  ) " +
            "from ExamSchedule s where s.startDate >= (:beginDate)  and s.endDate >= (:endDate) " +
            " or s.startDate <= (:beginDate) and  s.endDate >= (:endDate) or s.endDate >= (:beginDate) and  s.endDate <= (:endDate) "
            )
    public List<ScheduleDateVO> findScheduleByDateFilter(@Param("beginDate") Date beginDate ,@Param("endDate") Date endDate );

    @Query(value = "select distinct (s.stu_session) from jiaoda_member_student s order by s.stu_session limit 100",nativeQuery = true)
    public List<Integer> findStudentSession();

    //====================查找学院下的考试安排=====================
    @Query(value = "select count(distinct sm.scheduleId) from " +
            "schedule_majors sm,jiaoda_majors m,jiaoda_colleges c, exam_schedules s " +
            "where sm.majorId = m.id and m.college_id = c.id and sm.scheduleId = s.id " +
            "and c.id = ?1 and s.name like ?2", nativeQuery = true)
    public Long countForCollegeSchedule(Long collegeId,String nameLike);

    @Query(value = "select s.id,s.name,s.startDate from exam_schedules s where s.id in (" +
            "select distinct (sm.scheduleId) from schedule_majors sm,jiaoda_majors m,jiaoda_colleges c " +
            "where  sm.majorId = m.id and m.college_id = c.id and c.id = ?1) and s.name like ?2 " +
            "order by s.startDate DESC limit ?3, ?4", nativeQuery = true)
    public List<Object[]> findCollegeSchedule(Long collegeId,String nameLike,Integer offset,Integer size);

    @Query(value="from ExamScheduleViewVO v where v. id in (" +
            "select distinct (sm.scheduleId) from ScheduleMajor sm,Major m,College c " +
            "where sm.majorId = m.id and m.college.id = c.id and c.id = ?1) and v.name like ?2 " +
            "order by v.startDate desc" )
    public Page<ExamScheduleViewVO> findCollegeSchedule(Long collegeId,String nameLike,Pageable page);

    @Query(value="from ExamScheduleViewVO v where v. id in (" +
            "select distinct (sm.scheduleId) from ScheduleMajor sm,Major m,College c " +
            "where sm.majorId = m.id and m.college.id = c.id and c.id = ?1) and v.name like ?2 and v.tempid = ?3 " +
            "order by v.startDate desc" )
    public Page<ExamScheduleViewVO> findCollegeSchedule(Long collegeId,String nameLike,Long tempId,Pageable page);
    //===========================================================

    @Modifying
    @Query("update ExamSchedule set majorNames =:majorNames where id = :id")
    public void updateScheduleMajorNames(@Param("id")Long id,@Param("majorNames")String majorNames);
}
