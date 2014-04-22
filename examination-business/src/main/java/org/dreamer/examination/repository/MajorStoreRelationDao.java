package org.dreamer.examination.repository;

import org.dreamer.examination.entity.MajorStoreRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface MajorStoreRelationDao extends JpaRepository<MajorStoreRelation,Long>{

    public List<MajorStoreRelation> findByStoreId(Long storeId);

    @Modifying
    @Query("delete from MajorStoreRelation where storeId = ?1")
    public void deleteByStoreId(Long storeId);
}
