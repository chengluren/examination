package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamSchedule;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface ExamScheduleDao extends JpaRepository<ExamSchedule,Long>{

    @Query("select s.template.id from ExamSchedule s where s.major = (:major) and  current_time() " +
            "between s.startDate and s.endDate order by s.startDate desc")
    public List<Long> findScheduleByDate(@Param("major")String major,Pageable p);
}
