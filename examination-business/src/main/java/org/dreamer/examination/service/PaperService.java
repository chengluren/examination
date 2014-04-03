package org.dreamer.examination.service;

import org.dreamer.examination.entity.Paper;
import org.dreamer.examination.repository.PaperDao;
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
public class PaperService {

    @Autowired
    private PaperDao paperDao;

    public void addPaper(Paper paper){
         paperDao.save(paper);
    }

    public List<Paper> getPaper(long tempId){
        return paperDao.findByTemplateId(tempId);
    }

    public Page<Paper> getPaper(long tempId,int pageNum,int pageSize){
        Pageable p = new PageRequest(pageNum,pageSize);
        return paperDao.findByTemplateId(tempId,p);
    }

    public void deletePaper(long id){
        paperDao.delete(id);
    }

    public void deletePaper(Paper paper){
        paperDao.delete(paper);
    }
}
