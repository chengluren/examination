package org.dreamer.examination.repository;

import org.dreamer.examination.entity.QuestionStore;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 题库分类的DAO
 * Created by lcheng on 2014/3/30.
 */
public interface CategoryDao extends JpaRepository<QuestionStore,Long> {

}
