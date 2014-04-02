package org.dreamer.examination.service;

import org.dreamer.examination.entity.MajorStoreRelation;
import org.dreamer.examination.repository.MajorStoreRelationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Service
public class MajorStoreRelationService {

    @Autowired
    private MajorStoreRelationDao majorStoreRelationDao;

    public void addRelation(MajorStoreRelation relation){
        majorStoreRelationDao.save(relation);
    }

    public void addRelation(List<MajorStoreRelation> relations){
        majorStoreRelationDao.save(relations);
    }

    public void deleteRelation(MajorStoreRelation relation){
        majorStoreRelationDao.delete(relation);
    }

    public void deleteRelation(long id){
        majorStoreRelationDao.delete(id);
    }
}
