package org.dreamer.examination.repository;

import org.dreamer.examination.entity.Question;
import org.dreamer.examination.entity.QuestionVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.print.attribute.standard.PageRanges;
import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */

public interface QuestionDao extends JpaRepository<Question, Long> {

//    public Long countByStore(Long store;

    /**
     * 按类型分组查询XX题库中每种题型的的数量。主要用于统计
     * @param storeId
     * @return
     */
    @Query(value = "select ques_type,count(*) from questions where storeId= :storeId group by ques_type",
            nativeQuery = true)
    public List<Object[]> countForType(@Param("storeId") Long storeId);

    /**
     * XX题库中非必选题 某个题型的数量。主要用于随机生成试题
     * @param storeId
     * @param type
     * @return
     */
    @Query(value = "select count(q.id) from Question q where q.storeId= :storeId and TYPE(q)= :type and mustChoose is false")
    public long countOfTypeNotMust(@Param("storeId") Long storeId, @Param("type") Class<?> type);

    /**
     * 某题库下的某类型的题目数量
     * @param storeId
     * @param type
     * @return
     */
    @Query(value = "select count(q.id) from Question q where q.storeId= :storeId and TYPE(q)= :type")
    public long countOfType(@Param("storeId") Long storeId, @Param("type") Class<?> type);

    /**
     * XX 题库中必选题 按题型分组的数量统计
     * @param storeId
     * @return
     */
    @Query(value = "select ques_type,count(*) from questions where storeId= :storeId and mustChoose=1 " +
            "group by ques_type",  nativeQuery = true)
    public List<Object[]> countMustChooseForStore(@Param("storeId") Long storeId);

    /**
     * 分页获得某题库下某类型的试题（非必选）Id
     * @param storeId
     * @param type
     * @param pageable
     * @return
     */
    @Query(value = "select q.id from Question q  where q.storeId= (:storeId) and TYPE(q)= (:type) and mustChoose is false")
    public List<Long> findIdsByStoreAndTypeNotMust(@Param("storeId") Long storeId,
                                                   @Param("type") Class<?> type, Pageable pageable);

    /**
     * 分页获得某题库下某类型试题的Id
     * @param storeId
     * @param type
     * @param pageable
     * @return
     */
    @Query(value = "select q.id from Question q  where q.storeId= (:storeId) and TYPE(q)= (:type)")
    public List<Long> findIdsByStoreAndType(@Param("storeId") Long storeId,
                                            @Param("type") Class<?> type, Pageable pageable);

    /**
     * 分页获取某题库中，某类型的题目
     * @param storeId
     * @param type
     * @param pageable
     * @return
     */
    @Query(value = "from Question q where q.storeId = (:storeId) and TYPE(q) = (:type)")
    public Page<Question> findQuestions(@Param("storeId")Long storeId, @Param("type")Class<?> type,Pageable pageable);

    /**
     * 分页获取必选题基本信息
     * @param storeId
     * @param type
     * @param pageable
     * @return
     */
    @Query(value = "select new org.dreamer.examination.entity.QuestionVO(q.id,q.stem,q.answer) " +
            "from Question q where q.storeId = (:storeId) and TYPE(q) = (:type)")
    public Page<QuestionVO> findMustChooseQuestion(@Param("storeId")Long storeId, @Param("type")Class<?> type,Pageable pageable);

    @Modifying
    @Query("update Question q set q.stem = ?1,q.answer =?2,q.mustChoose = ?3,q.imgPath =?4 where q.id = ?5")
    public void updateQuestion(String stem,String answer,boolean mustChoose,String imgPath,Long id);

    @Modifying
    @Query("delete from Question where storeId = ?1")
    public void deleteStoreQuestions(long storeId);


}
