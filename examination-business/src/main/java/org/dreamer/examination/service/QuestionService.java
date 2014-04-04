package org.dreamer.examination.service;

import org.dreamer.examination.entity.ChoiceQuestion;
import org.dreamer.examination.entity.Question;
import org.dreamer.examination.entity.TrueOrFalseQuestion;
import org.dreamer.examination.entity.Types;
import org.dreamer.examination.repository.ChoiceQuestionDao;
import org.dreamer.examination.repository.QuestionDao;
import org.dreamer.examination.repository.TrueOrFalseQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private ChoiceQuestionDao choiceQuestionDao;
    @Autowired
    private TrueOrFalseQuestionDao trueFalseQuestionDao;

    /**
     * 添加试题
     *
     * @param question
     */
    public void addQuestion(Question question) {
        questionDao.save(question);
    }

    /**
     * 批量添加试题
     *
     * @param questions
     */
    public void addQuestion(Iterable<Question> questions) {
        questionDao.save(questions);
    }

    @Deprecated
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    public void deleteQuestion(Question question) {
        questionDao.delete(question);
    }

    public Page<ChoiceQuestion> getChoiceQuestions(long storeId, int pageNum, int pageSize) {
        Pageable pr = new PageRequest(pageNum, pageSize);
        return choiceQuestionDao.findByStoreId(storeId, pr);
    }

    public Page<ChoiceQuestion> getChoiceQuestions(long storeId, boolean multiple, int pageNum, int pageSize) {
        Pageable pr = new PageRequest(pageNum, pageSize);
        return choiceQuestionDao.findByStoreIdAndMultiple(storeId, multiple, pr);
    }

    public Page<TrueOrFalseQuestion> getTrueFalseQuestions(long storeId, int pageNum, int pageSize) {
        Pageable pr = new PageRequest(pageNum, pageSize);
        return trueFalseQuestionDao.findByStoreId(storeId, pr);
    }

    /**
     * XX 题库中，某类型的试题的总数
     * @param storeId
     * @param type
     * @return
     */
    public long countOfStoreTypedQues(long storeId, Types.QuestionType type) {
        String qtype = getNativeQuesType(type);
        return questionDao.countByStoreIdOfTypeNotMust(storeId,qtype);
    }

    public List<Long> getQuesIdsOfStoreWithType(long storeId, Types.QuestionType type,
                                                int pageNum,int pageSize){
        Pageable p = new PageRequest(pageNum,pageSize);
        return questionDao.findQuesIdsByStoreIdOfTypeNotMust(storeId,getNativeQuesType(type),p);
    }

    private String getNativeQuesType(Types.QuestionType type){
        String qtype = "";
        switch (type){
            case Choice:
                qtype = "CH";
                break;
            case MultipleChoice:
                qtype ="MC";
                break;
            case TrueFalse:
                qtype = "TF";
                break;
            case Completion:
                qtype = "CO";
                break;
        }
        return qtype;
    }
}
