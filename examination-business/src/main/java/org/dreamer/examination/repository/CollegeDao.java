package org.dreamer.examination.repository;

import org.dreamer.examination.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lcheng on 2014/5/11.
 */
public interface CollegeDao extends JpaRepository<College, Long> {

    public List<College> findByName(String name);
}
