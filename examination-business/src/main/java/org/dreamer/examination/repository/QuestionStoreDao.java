package org.dreamer.examination.repository;

import org.dreamer.examination.entity.QuestionStore;
import org.dreamer.examination.vo.QuestionStoreVO;
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
public interface QuestionStoreDao extends JpaRepository<QuestionStore, Long> {

    @Query(value = "SELECT s FROM QuestionStore s, MajorStoreRelation r where s.id = r.storeId and r.major = :major")
    public Page<QuestionStore> findStoreForMajor(@Param("major") String major, Pageable pageable);

    @Query(value = "SELECT s FROM QuestionStore s, MajorStoreRelation r where s.id = r.storeId and r.major = :major")
    public List<QuestionStore> findStoreForMajor(@Param("major") String major);

    @Query(value = "SELECT s FROM QuestionStore s where s.id in (" +
            "select distinct ms.storeId from MajorStoreRelation ms where cast(ms.major as long) in (" +
            "select m.id from Major m where m.college.id = ?1))")
    public List<QuestionStore> findStoreForCollege(Long collegeId);

    public List<QuestionStore> findByGeneric(boolean generic);

    @Query(value = "select new org.dreamer.examination.vo.QuestionStoreVO(s.id,s.name,(" +
            "select count(q.id) from Question q where q.storeId= s.id) as quesCount,s.generic,s.comment) " +
            "from QuestionStore s order by quesCount desc",
            countQuery = "select count(s.id) from QuestionStore s")
    public Page<QuestionStoreVO> findStoreBaseAndQuesCountInfo(Pageable page);

    @Query(value = "select new org.dreamer.examination.vo.QuestionStoreVO(s.id,s.name,(" +
            "select count(q.id) from Question q where q.storeId= s.id) as quesCount,s.generic,s.comment) " +
            "from QuestionStore s")
    public List<QuestionStoreVO> findAllStoreBaseAndQuesCountInfo();

}
