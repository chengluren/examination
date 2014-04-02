package org.dreamer.examination.repository;

import org.dreamer.examination.entity.QuestionStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * 题库分类的DAO
 * Created by lcheng on 2014/3/30.
 */
public interface QuestionStoreDao extends JpaRepository<QuestionStore,Long> {

    @Query(value = "SELECT s FROM QuestionStore as s WHERE s.id in " +
            "(SELECT r.storeId FROM MajorStoreRelation as r WHERE r.major= :major)")
    public Page<QuestionStore> findStoreForMajor(@Param("major")String major,Pageable pageable);


}
