package org.dreamer.examination.service;

import org.dreamer.examination.entity.*;
import org.dreamer.examination.repository.ChoiceQuestionDao;
import org.dreamer.examination.repository.QuestionDao;
import org.dreamer.examination.repository.QuestionOptionDao;
import org.dreamer.examination.repository.TrueOrFalseQuestionDao;
import org.dreamer.examination.utils.QuestionTypeUtils;
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
    @Autowired
    private QuestionOptionDao optionDao;

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

    public Question getQuestion(long id) {
        return questionDao.findOne(id);
    }

    public Page<ChoiceQuestion> getChoiceQuestions(long storeId, int pageNum, int pageSize) {
        Pageable pr = new PageRequest(pageNum, pageSize);
        return choiceQuestionDao.findByStoreId(storeId, pr);
    }

    public Page<TrueOrFalseQuestion> getTrueFalseQuestions(long storeId, int pageNum, int pageSize) {
        Pageable pr = new PageRequest(pageNum, pageSize);
        return trueFalseQuestionDao.findByStoreId(storeId, pr);
    }

    /**
     * XX 题库中，某类型试题(非必选)的总数
     *
     * @param storeId
     * @param type
     * @return
     */
    public long countOfTypeQuestionNotMust(long storeId, Types.QuestionType type) {
        //String qtype = type.getShortName();
        Class<?> clazz = QuestionTypeUtils.getClassType(type);
        return questionDao.countOfTypeNotMust(storeId, clazz);
    }

    /**
     * XX题库中某类型的试题总数
     *
     * @param storeId
     * @param type
     * @return
     */
    public long countOfTypeQuestion(long storeId, Types.QuestionType type) {
        Class<?> clazz = QuestionTypeUtils.getClassType(type);
        return questionDao.countOfType(storeId, clazz);
    }

    /**
     * 分页获得非必选题的Id列表
     *
     * @param storeId
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Long> getIdsNotMust(long storeId, Types.QuestionType type,
                                    int pageNum, int pageSize) {
        Pageable p = new PageRequest(pageNum, pageSize);
        Class<?> clazz = QuestionTypeUtils.getClassType(type);
        return questionDao.findIdsByStoreAndTypeNotMust(storeId, clazz, p);
    }

    /**
     * 分页获得非必选题的Id列表
     *
     * @param storeId
     * @param type
     * @param p
     * @return
     */
    public List<Long> getIdsNotMust(long storeId, Types.QuestionType type, Pageable p) {
        Class<?> clazz = QuestionTypeUtils.getClassType(type);
        return questionDao.findIdsByStoreAndTypeNotMust(storeId, clazz, p);
    }

    /**
     * 分页获得试题Id列表
     *
     * @param storeId
     * @param type
     * @param p
     * @return
     */
    public List<Long> getIds(long storeId, Types.QuestionType type, Pageable p) {
        Class<?> clazz = QuestionTypeUtils.getClassType(type);
        return questionDao.findIdsByStoreAndType(storeId, clazz, p);
    }

    /**
     * 分页获得试题Id列表
     *
     * @param storeId
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Long> getIds(long storeId, Types.QuestionType type, int pageNum, int pageSize) {
        Pageable p = new PageRequest(pageNum, pageSize);
        Class<?> clazz = QuestionTypeUtils.getClassType(type);
        return questionDao.findIdsByStoreAndType(storeId, clazz, p);
    }

    public Page<Question> getQuestions(long storeId, Types.QuestionType type, Pageable p) {
        Class<?> clazz = QuestionTypeUtils.getClassType(type);
        return questionDao.findQuestions(storeId, clazz, p);
    }

    /**
     * 分页获得必考题的基本信息
     * @param storeId
     * @param type
     * @param p
     * @return
     */
    public Page<QuestionVO> getMustChooseQuestion(Long storeId, Types.QuestionType type, Pageable p){
        Class<?> clazz = QuestionTypeUtils.getClassType(type);
        return questionDao.findMustChooseQuestion(storeId, clazz, p);
    }

    public Page<QuestionVO> getMustChooseQuestionNotChoosed(Long storeId, Types.QuestionType type,Long tempId,Pageable p){
        Class<?> clazz = QuestionTypeUtils.getClassType(type);
        return questionDao.findMustChooseQuestionNotChoosed(storeId, clazz,tempId, p);
    }

    public Page<Question> getQuestions(long storeId, Types.QuestionType type, int pageNum, int pageSize) {
        Pageable p = new PageRequest(pageNum, pageSize);
        Class<?> clazz = QuestionTypeUtils.getClassType(type);
        return questionDao.findQuestions(storeId, clazz, p);
    }

    public void deleteQuestion(Question question) {
        questionDao.delete(question);
    }

    public void deleteQuestion(Long id) {
        questionDao.delete(id);
    }

    public void deleteQuestionOption(Long opId) {
        optionDao.delete(opId);
    }

    public Long getQuestionCount()
    {
       return  questionDao.getQuestionCount() == null ? 0L :questionDao.getQuestionCount();
    }
}
