package org.dreamer.examination.repository;

import org.dreamer.examination.entity.QuestionStore;
import org.dreamer.examination.entity.QuestionStoreVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * 题库分类的DAO
 * Created by lcheng on 2014/3/30.
 */
public interface QuestionStoreDao extends JpaRepository<QuestionStore,Long> {

    @Query(value = "SELECT s FROM QuestionStore s, MajorStoreRelation r where s.id = r.storeId and r.major = :major")
    public Page<QuestionStore> findStoreForMajor(@Param("major")String major,Pageable pageable);

    @Query(value = "SELECT s FROM QuestionStore s, MajorStoreRelation r where s.id = r.storeId and r.major = :major")
    public List<QuestionStore> findStoreForMajor(@Param("major")String major);

    @Query(value = "select new org.dreamer.examination.entity.QuestionStoreVO(s.id,s.name,count(q.id) as quesCount,s.comment) " +
            "from QuestionStore s left join Question q " +
            "on s.id = q.storeId group by s.id")
    public Page<QuestionStoreVO> findStoreBaseAndQuesCountInfo(Pageable page);

}
