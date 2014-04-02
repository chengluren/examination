package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface ExamScheduleDao extends JpaRepository<ExamSchedule,Long>{
}
