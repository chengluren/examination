package org.dreamer.examination.repository;

import org.dreamer.examination.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */

public interface QuestionDao extends JpaRepository<Question,Long> {

}
