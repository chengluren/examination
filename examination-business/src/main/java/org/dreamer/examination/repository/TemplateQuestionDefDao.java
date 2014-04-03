package org.dreamer.examination.repository;

import org.dreamer.examination.entity.TemplateQuestionDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface TemplateQuestionDefDao extends JpaRepository<TemplateQuestionDef, Long> {

    @Modifying
    @Query("delete from TemplateQuestionDef where template.id = :tempId")
    public void deleteDefsForTemplate(@Param("tempId")long tempId);
}
