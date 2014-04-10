package org.dreamer.examination.repository;

import org.dreamer.examination.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
     * @param store
     * @return
     */
    @Query(value = "select ques_type,count(*) from questions where store= :store group by ques_type",
            nativeQuery = true)
    public List<Object[]> countForType(@Param("store") Long store);

    /**
     * XX题库中非必选题 某个题型的数量。主要用于随机生成试题
     * @param store
     * @param type
     * @return
     */
    @Query(value = "select count(*) from questions where store= :store and ques_type= :type and mustChoose !=1",
            nativeQuery = true)
    public long countOfTypeNotMust(@Param("store") Long store, @Param("type") String type);

    /**
     * XX 题库中必选题 按题型分组的数量统计
     * @param store
     * @return
     */
    @Query(value = "select ques_type,count(*) from questions where store= :store and mustChoose=1 " +
            "group by ques_type",  nativeQuery = true)
    public List<Object[]> countMustChooseForStore(@Param("store") Long store);

    /**
     * 分页获得符合条件的试题Id
     * @param store
     * @param type
     * @param pageable
     * @return
     */
    @Query(value = "select q.id from Question q  where q.store= (:store) and TYPE(q)= (:type) and mustChoose is false")
    public List<Long> findIdsByStoreAndTypeNotMust(@Param("store") Long store,
                                                   @Param("type") String type, Pageable pageable);
}
