package org.dreamer.examination.service;

import org.dreamer.examination.entity.QuestionStore;
import org.dreamer.examination.repository.QuestionStoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题库的服务
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Service
public class QuestionStoreService {

    @Autowired
    private QuestionStoreDao questionStoreDao;

    public void addQuestionStore(QuestionStore store){
        questionStoreDao.save(store);
    }

    public List<QuestionStore> getAll(){
        return questionStoreDao.findAll();
    }

    public Page<QuestionStore> getAll(int pageNum,int pageSize){
        Pageable pr = new PageRequest(pageNum,pageSize);
        return questionStoreDao.findAll(pr);
    }

    public Page<QuestionStore> getStoreForMajor(String major,int pageNum,int pageSize){
        Pageable pr = new PageRequest(pageNum,pageSize);
        return questionStoreDao.findStoreForMajor(major,pr);
    }
}
