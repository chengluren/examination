package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamSchedule;
import org.dreamer.examination.entity.ExamScheduleVO;
import org.dreamer.examination.entity.ScheduleDateVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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

    public int countByTemplateId(Long tempId);

    @Query("select s.template.id from ExamSchedule s where s.major = (:major) and  current_time() " +
            "between s.startDate and s.endDate order by s.startDate desc")
    public List<Long> findScheduleByDate(@Param("major")String major,Pageable p);

    @Query("from ExamSchedule s where s.major = (:major) and  current_time() " +
            "between s.startDate and s.endDate order by s.startDate desc")
    public List<ExamSchedule> findScheduleByDate(@Param("major")String major);

    @Query("select new org.dreamer.examination.entity.ExamScheduleVO(s.name,s.startDate,s.endDate,s.id) " +
            "from ExamSchedule s where current_time() < s.startDate and s.major = ?1")
    public List<ExamScheduleVO> findSchedule(String major);


    @Query("select new org.dreamer.examination.entity.ScheduleDateVO( s.id , s.name,s.startDate,s.endDate  ) " +
            "from ExamSchedule s where s.startDate >= (:beginDate)  and s.endDate >= (:endDate) " +
            " or s.startDate <= (:beginDate) and  s.endDate >= (:endDate) or s.endDate >= (:beginDate) and  s.endDate <= (:endDate) "
            )
    public List<ScheduleDateVO> findScheduleByDateFilter(@Param("beginDate") Date beginDate ,@Param("endDate") Date endDate );


}
