package org.dreamer.examination.repository;

import org.dreamer.examination.entity.QuestionStore;
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

    @Query(value = "SELECT s FROM QuestionStore s, MajorStoreRelation r where s.id = r.storeId and s.major = :major")
    public Page<QuestionStore> findStoreForMajor(@Param("major")String major,Pageable pageable);

    @Query(value = "SELECT s FROM QuestionStore s, MajorStoreRelation r where s.id = r.storeId and s.major = :major")
    public List<QuestionStore> findStoreForMajor(@Param("major")String major);

}
