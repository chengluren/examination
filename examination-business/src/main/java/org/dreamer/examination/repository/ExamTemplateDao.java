package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 考试方案模板的DAO
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface ExamTemplateDao extends JpaRepository<ExamTemplate,Long> {

    public List<ExamTemplate> findByName(String name);

    public List<ExamTemplate> findByNameLike(String likeName);

    public Page<ExamTemplate> findByName(String name,Pageable pageable);

    public Page<ExamTemplate> findByNameLike(String likeName,Pageable pageable);
}
