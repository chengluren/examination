package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ScheduleMajor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lcheng on 2014/5/26.
 */
public interface ScheduleMajorDao extends JpaRepository<ScheduleMajor, Long> {

    @Query(value="select sm.majorId from ScheduleMajor sm where sm.scheduleId = ?1")
    public List<String> findByScheduleId(Long scheduleId);

    @Query(value="select m.name from ScheduleMajor sm,Major m where sm.majorId = m.id and sm.scheduleId = ?1")
    public List<String> findMajorNamesByScheduleId(Long scheduleId);

    @Query(value = "delete from ScheduleMajor where scheduleId = ?1")
    @Modifying
    public void deleteByScheduleId(Long scheduleId);
}
