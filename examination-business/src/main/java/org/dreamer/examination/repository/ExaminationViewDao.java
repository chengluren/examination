package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamRecordVO;
import org.dreamer.examination.entity.Examination;
import org.dreamer.examination.entity.ExaminationViewVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface ExaminationViewDao extends CommonRepository<ExaminationViewVO,Long>{

}
