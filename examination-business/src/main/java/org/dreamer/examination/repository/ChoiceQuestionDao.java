package org.dreamer.examination.repository;

import org.dreamer.examination.entity.ChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lcheng
 * @version 1.0
 *
 */
public interface ChoiceQuestionDao extends JpaRepository<ChoiceQuestion,Long>{
}
