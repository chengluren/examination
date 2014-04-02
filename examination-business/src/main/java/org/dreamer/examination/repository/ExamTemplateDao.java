package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 考试方案模板的DAO
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface ExamTemplateDao extends JpaRepository<ExamTemplate,Long> {
}
