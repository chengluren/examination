package org.dreamer.examination.repository;

import org.dreamer.examination.entity.MustChooseQuestionDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface MustChooseQuestionDefDao extends JpaRepository<MustChooseQuestionDef,Long>{

    @Modifying
    @Query("delete from MustChooseQuestionDef where template.id = :tempId")
    public void deleteDefsForTemplate(@Param("tempId")long tempId);

    public List<MustChooseQuestionDef> findByTemplateId(long tempId);
}
