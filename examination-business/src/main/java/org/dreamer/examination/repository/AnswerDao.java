package org.dreamer.examination.repository;

import org.dreamer.examination.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lcheng on 2014/4/13.
 */
public interface AnswerDao extends JpaRepository<Answer,Long>{

    @Modifying
    @Query(value = "delete from Answer where examId = ?1 and quesId = ?2")
    public void deleteByExamIdAndQuesId(long examId,long quesId);


//    public void updateAnswer(Answer a);

    public int countByExamIdAndQuesId(long examId,long quesId);
}
