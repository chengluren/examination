package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ChoiceQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lcheng
 * @version 1.0
 *
 */
public interface ChoiceQuestionDao extends JpaRepository<ChoiceQuestion,Long>{
    /**
     * 按题库获得选择题
     *
     * @param store
     * @param pageable
     * @return
     */
    public Page<ChoiceQuestion> findByStore(long store, Pageable pageable);

    /**
     * 按库获得选择题的数量
     * @param store
     * @return
     */
    public Long countByStore(long store);

}
