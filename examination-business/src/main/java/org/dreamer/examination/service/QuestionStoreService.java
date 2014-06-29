package org.dreamer.examination.service;

import org.dreamer.examination.entity.MajorStoreRelation;
import org.dreamer.examination.entity.QuestionStore;
import org.dreamer.examination.vo.QuestionStoreVO;
import org.dreamer.examination.repository.MajorStoreRelationDao;
import org.dreamer.examination.repository.QuestionDao;
import org.dreamer.examination.repository.QuestionOptionDao;
import org.dreamer.examination.repository.QuestionStoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 题库的服务
 *
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Service
public class QuestionStoreService {

    @Autowired
    private QuestionStoreDao questionStoreDao;
    @Autowired
    private MajorStoreRelationDao majorStoreRelDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionOptionDao optionDao;

    public long getStoreCount(){
        return questionStoreDao.count();
    }

    public void addQuestionStore(QuestionStore store) {
        questionStoreDao.save(store);
    }

    public void addQuestionStore(QuestionStore store, String[] majors) {
        questionStoreDao.save(store);
        long sid = store.getId();
        if (majors != null && majors.length > 0) {
            List<MajorStoreRelation> rels = parseRelation(majors, sid);
            majorStoreRelDao.save(rels);
        }
    }

    public void updateQuestionStore(QuestionStore store, String[] majors) {
        if (store != null && store.getId() != null) {
            Long sid = store.getId();
            majorStoreRelDao.deleteByStoreId(sid);
            questionStoreDao.save(store);
            if (!store.isGeneric()){
                List<MajorStoreRelation> rels = parseRelation(majors, sid);
                majorStoreRelDao.save(rels);
            }else{
                majorStoreRelDao.deleteByStoreId(sid);
            }
        }
    }

    public List<QuestionStore> getAll() {
        return questionStoreDao.findAll();
    }

    public List<QuestionStore> getCollegeStore(Long collegeId) {
        List<QuestionStore> generic = questionStoreDao.findByGeneric(true);
        generic.addAll(questionStoreDao.findStoreForCollege(collegeId));
        return generic;
    }

    public QuestionStore getStore(long id) {
        return questionStoreDao.findOne(id);
    }

    public List<MajorStoreRelation> getMajorStoreRelation(long storeId) {
        return majorStoreRelDao.findByStoreId(storeId);
    }

    public Page<QuestionStoreVO> getStoreAndQuesCountInfo(Pageable page) {
        return questionStoreDao.findStoreBaseAndQuesCountInfo(page);
    }

    /**
     * 获得专业可以访问的题库（通识类题库所有专业可以访问）
     * @param major
     * @return
     */
    public List<QuestionStore> getStoreForMajor(Long major) {
        List<QuestionStore> stores = questionStoreDao.findByGeneric(true);
        stores.addAll(questionStoreDao.findStoreForMajor(major));
        return stores;
    }

    public void deleteStore(long storeId) {
        questionStoreDao.delete(storeId);
        majorStoreRelDao.deleteByStoreId(storeId);
        optionDao.deleteQuestionOptions(storeId);
        questionDao.deleteStoreQuestions(storeId);
    }

    private List<MajorStoreRelation> parseRelation(String[] majors, long storeId) {
        if (majors == null || majors.length == 0) {
            return new ArrayList<>();
        }
        List<MajorStoreRelation> rels = new ArrayList<>();
        for (String major : majors) {
            rels.add(new MajorStoreRelation(storeId, Long.valueOf(major)));
        }
        return rels;
    }
}
