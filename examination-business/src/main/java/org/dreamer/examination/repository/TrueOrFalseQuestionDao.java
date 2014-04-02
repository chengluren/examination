package org.dreamer.examination.repository;

import org.dreamer.examination.entity.TrueOrFalseQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface TrueOrFalseQuestionDao extends JpaRepository<TrueOrFalseQuestion,Long>{
    /**
     * 按库获得选择题的数量
     * @param storeId
     * @return
     */
    public Long countByStoreId(long storeId);

    /**
     * 按库获得判断题
     * @param storeId
     * @param pageable
     * @return
     */
    public Page<TrueOrFalseQuestion> findByStoreId(long storeId,Pageable pageable);
}
