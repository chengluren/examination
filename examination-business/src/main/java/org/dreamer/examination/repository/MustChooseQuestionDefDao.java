package org.dreamer.examination.repository;

import org.dreamer.examination.entity.MustChooseQuestionDef;
import org.dreamer.examination.entity.Types;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface MustChooseQuestionDefDao extends JpaRepository<MustChooseQuestionDef,Long>{

    public List<MustChooseQuestionDef> findByTemplateId(long tempId);

    @Query("select distinct d.questionType from MustChooseQuestionDef d where d.template.id = :tempId")
    public Set<Types.QuestionType> findDistinctQuesTypes(@Param("tempId")long tempId);

    @Modifying
    @Query("delete from MustChooseQuestionDef where template.id = :tempId")
    public void deleteDefsForTemplate(@Param("tempId")long tempId);


    @Query("select d.id,d.questionId,q.stem,q.answer,d.quesScore,d.questionType " +
            "from MustChooseQuestionDef d,Question q where d.questionId = q.id and d.template.id = ?1")
    public Page<Object[]> findMustChooseDefs(Long tempId,Pageable pageable);
}
