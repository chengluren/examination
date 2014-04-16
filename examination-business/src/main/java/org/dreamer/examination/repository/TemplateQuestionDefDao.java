package org.dreamer.examination.repository;

import org.dreamer.examination.entity.TemplateQuestionDef;
import org.dreamer.examination.entity.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface TemplateQuestionDefDao extends JpaRepository<TemplateQuestionDef, Long> {

    @Query("select distinct d.questionType from TemplateQuestionDef d where d.template.id = :tempId")
    public Set<Types.QuestionType> findDistinctQuesTypes(@Param("tempId")long tempId);

    @Modifying
    @Query("delete from TemplateQuestionDef where template.id = :tempId")
    public void deleteDefs(@Param("tempId") long tempId);
}
