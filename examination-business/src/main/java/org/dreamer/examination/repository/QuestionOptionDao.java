package org.dreamer.examination.repository;

import org.dreamer.examination.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface QuestionOptionDao extends JpaRepository<QuestionOption,Long> {

    @Modifying
    @Query(value = "delete from options where ques_id in " +
            "(select q.id from questions q where q.storeId = ?1)",nativeQuery = true)
    public void deleteQuestionOptions(Long storeId);
}
