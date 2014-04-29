package org.dreamer.examination.repository;


import org.dreamer.examination.entity.ExaminationViewPassRateVO;
import org.springframework.data.jpa.repository.Query;

/**
 * @author xwang
 * @version 1.0
 *          ${tags}
 */
public interface ExaminationViewPassRateDao extends CommonRepository<ExaminationViewPassRateVO,Long>{

    @Query(value = "select sum(ev.passrate)/count(ev.id) from ExaminationViewPassRateVO ev")
    public Double getAveragePassRate();
}
