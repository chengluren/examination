package org.dreamer.examination.repository;

import org.dreamer.examination.entity.PaperQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lcheng on 2014/4/17.
 */
public interface PaperQuestionDao extends JpaRepository<PaperQuestion,Long>{

    public List<PaperQuestion> findByPaperId(long paperId);
    @Deprecated
    public int countByExamIdAndPaperId(Long examId,Long pid);
}
