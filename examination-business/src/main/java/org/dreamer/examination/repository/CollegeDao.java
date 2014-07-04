package org.dreamer.examination.repository;

import org.dreamer.examination.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lcheng on 2014/5/11.
 */
public interface CollegeDao extends JpaRepository<College, Long> {

    public List<College> findByName(String name);

    @Query(value = "select stu_collage_id,stu_education,stu_session,stu_professional_id from jiaoda_member_student where stu_id = ?1",nativeQuery = true)
    public List<Object[]> findStudentBaseInfo(Long stuId);
}
