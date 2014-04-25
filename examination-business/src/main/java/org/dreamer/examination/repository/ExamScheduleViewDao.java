package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamSchedule;
import org.dreamer.examination.entity.ExamScheduleVO;
import org.dreamer.examination.entity.ExamScheduleViewVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author xwang
 * @version 1.0
 *          ${tags}
 */
public interface ExamScheduleViewDao extends CommonRepository<ExamScheduleViewVO,Long>{


}
