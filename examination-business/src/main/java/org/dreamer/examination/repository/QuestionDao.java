package org.dreamer.examination.repository;

import org.dreamer.examination.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */

public interface QuestionDao extends JpaRepository<Question, Long> {

    public Long countByStoreId(long storeId);

    /**
     * 按类型分组查询XX题库中每种题型的的数量。主要用于统计
     * @param storeId
     * @return
     */
    @Query(value = "select ques_type,count(*) from questions where storeId= :storeId group by ques_type",
            nativeQuery = true)
    public List<Object[]> countByStoreIdForType(@Param("storeId")long storeId);

    /**
     * XX题库中非必选题 某个题型的数量。主要用于随机生成试题
     * @param storeId
     * @param type
     * @return
     */
    @Query(value = "select count(*) from questions where storeId= :storeId and ques_type= :type and mustChoose !=1",
            nativeQuery = true)
    public long countByStoreIdOfTypeNotMust(@Param("storeId")long storeId,@Param("type")String type);

    /**
     * XX 题库中必选题 按题型分组的数量统计
     * @param storeId
     * @return
     */
    @Query(value = "select ques_type,count(*) from questions where storeId= :storeId and mustChoose=1 " +
            "group by ques_type",  nativeQuery = true)
    public List<Object[]> countMustChooseByStoreId(@Param("storeId")long storeId);

    /**
     * 分页获得符合条件的试题Id
     * @param storeId
     * @param type
     * @param pageable
     * @return
     */
    @Query(value = "select id from questions where storeId= :storeId and ques_type= :type and mustChoose !=1",
            nativeQuery = true)
    public List<Long> findQuesIdsByStoreIdOfTypeNotMust(@Param("storeId")long storeId,
                                                        @Param("type")String type,Pageable pageable);
}
