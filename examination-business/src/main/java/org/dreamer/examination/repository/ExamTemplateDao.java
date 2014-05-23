package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ExamTemplate;
import org.dreamer.examination.vo.BaseInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 考试方案模板的DAO
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface ExamTemplateDao extends JpaRepository<ExamTemplate,Long> {

    public List<ExamTemplate> findByName(String name);

    public List<ExamTemplate> findByNameLike(String likeName);

    public Page<ExamTemplate> findByName(String name,Pageable pageable);

    public Page<ExamTemplate> findByNameLike(String likeName,Pageable pageable);

    @Query("select new org.dreamer.examination.vo.BaseInfoVO(t.id,t.name) from ExamTemplate t where t.name like ?1")
    public Page<BaseInfoVO> findNameInfo(String likeName,Pageable pageable);

    @Query("select t.id,t.name,t.passScore,t.multiChoiceMixedInChoice from ExamTemplate t where t.id = ?1")
    public List<Object[]> findTemplateBaseInfo(Long tempId);

    @Modifying
    @ Query("update ExamTemplate set name=:name,passScore=:passScore,multiChoiceMixedInChoice=:mixedIn where id =:tempId ")
    public void updateTemplate(@Param("tempId")Long tempId,@Param("name")String name,
                               @Param("passScore")float passScore,@Param("mixedIn")boolean mixedIn);

    @Query(value = "select count(t.id) from ExamTemplate t")
    public Long getTemplateCount();
}
