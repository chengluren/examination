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

    public Question getQuestion(long id){
       return questionDao.findOne(id);
    }

    public void deleteQuestion(Question question) {
        questionDao.delete(question);
    }

    public Page<ChoiceQuestion> getChoiceQuestions(long storeId, int pageNum, int pageSize) {
        Pageable pr = new PageRequest(pageNum, pageSize);
        return choiceQuestionDao.findByStore(storeId, pr);
    }

    public Page<TrueOrFalseQuestion> getTrueFalseQuestions(long storeId, int pageNum, int pageSize) {
        Pageable pr = new PageRequest(pageNum, pageSize);
        return trueFalseQuestionDao.findByStore(storeId, pr);
    }

    /**
     * XX 题库中，某类型试题(非必选)的总数
     * @param storeId
     * @param type
     * @return
     */
    public long countOfTypeQuestionNotMust(long storeId, Types.QuestionType type) {
        String qtype = type.getShortName();
        return questionDao.countOfTypeNotMust(storeId, qtype);
    }

    /**
     * XX题库中某类型的试题总数
     * @param storeId
     * @param type
     * @return
     */
    public long countOfTypeQuestion(long storeId, Types.QuestionType type){
        return questionDao.countOfType(storeId,type.getShortName());
    }

    /**
     * 分页获得非必选题的Id列表
     * @param storeId
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Long> getIdsNotMust(long storeId, Types.QuestionType type,
                                    int pageNum, int pageSize){
        Pageable p = new PageRequest(pageNum,pageSize);
        return questionDao.findIdsByStoreAndTypeNotMust(storeId, type.getShortName(), p);
    }

    /**
     * 分页获得非必选题的Id列表
     * @param storeId
     * @param type
     * @param p
     * @return
     */
    public List<Long> getIdsNotMust(long storeId,Types.QuestionType type,Pageable p){
        return questionDao.findIdsByStoreAndTypeNotMust(storeId,type.getShortName(),p);
    }

    /**
     * 分页获得试题Id列表
     * @param storeId
     * @param type
     * @param p
     * @return
     */
    public List<Long> getIds(long storeId,Types.QuestionType type,Pageable p){
        return questionDao.findIdsByStoreAndType(storeId,type.getShortName(),p);
    }

    /**
     * 分页获得试题Id列表
     * @param storeId
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Long> getIds(long storeId,Types.QuestionType type,int pageNum,int pageSize){
        Pageable p = new PageRequest(pageNum,pageSize);
        return questionDao.findIdsByStoreAndType(storeId,type.getShortName(),p);
    }

    public Page<Question> getQuestions(long stroreId,Types.QuestionType type,Pageable p){
        return questionDao.findQuestions(stroreId,type.getShortName(),p);
    }

    public Page<Question> getQuestions(long storeId,Types.QuestionType type,int pageNum,int pageSize){
        Pageable p = new PageRequest(pageNum,pageSize);
        return questionDao.findQuestions(storeId,type.getShortName(),p);
    }

}
