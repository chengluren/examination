package org.dreamer.examination.service;

import org.dreamer.examination.entity.MajorStoreRelation;
import org.dreamer.examination.entity.QuestionStore;
import org.dreamer.examination.entity.QuestionStoreVO;
import org.dreamer.examination.repository.MajorStoreRelationDao;
import org.dreamer.examination.repository.QuestionDao;
import org.dreamer.examination.repository.QuestionStoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            List<MajorStoreRelation> rels = parseRelation(majors, sid);
            majorStoreRelDao.save(rels);
        }
    }

    public List<QuestionStore> getAll() {
        return questionStoreDao.findAll();
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

    public List<QuestionStore> getStoreForMajor(String major) {
        return questionStoreDao.findStoreForMajor(major);
    }

    public void deleteStore(long storeId) {
        questionStoreDao.delete(storeId);
        majorStoreRelDao.deleteByStoreId(storeId);
        questionDao.deleteStoreQuestions(storeId);
    }

    private List<MajorStoreRelation> parseRelation(String[] majors, long storeId) {
        if (majors == null || majors.length == 0) {
            return new ArrayList<>();
        }
        List<MajorStoreRelation> rels = new ArrayList<>();
        for (String major : majors) {
            rels.add(new MajorStoreRelation(storeId, major));
        }
        return rels;
    }
}
