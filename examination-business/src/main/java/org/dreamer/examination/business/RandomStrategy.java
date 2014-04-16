package org.dreamer.examination.business;

import org.dreamer.examination.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface RandomStrategy {

    /**
     * 顺序加载某题库里 某类型的题目
     * @param storeId
     * @param quesType
     * @param p
     * @return
     */
    public Page<Question> sequenceLoad(long storeId,String quesType,Pageable p);

    /**
     * 顺序加载某题库里 某类型的题目
     * @param storeId
     * @param quesType
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<Question> sequenceLoad(long storeId,String quesType,int pageNum,int pageSize);

    /**
     * 随机加载某题库里 某类型的题目
     * @param storeId
     * @param quesType
     * @param size
     * @return
     */
    public List<Question> randomLoad(long storeId,String quesType,int size);
}
