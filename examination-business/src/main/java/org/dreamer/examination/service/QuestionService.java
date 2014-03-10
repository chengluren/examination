package org.dreamer.examination.service;

import org.dreamer.examination.entity.Question;
import org.dreamer.examination.repository.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public void addQuestion(Question question) {
        questionDao.save(question);
    }

    public List<Question> findAll() {
        return questionDao.findAll();
    }

    public void deleteQuestion(Question question) {
        questionDao.delete(question);
    }
}
