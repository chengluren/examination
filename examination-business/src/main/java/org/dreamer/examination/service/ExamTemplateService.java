package org.dreamer.examination.service;

import org.dreamer.examination.entity.ExamTemplate;
import org.dreamer.examination.entity.MustChooseQuestionDef;
import org.dreamer.examination.repository.ExamTemplateDao;
import org.dreamer.examination.repository.MustChooseQuestionDefDao;
import org.dreamer.examination.repository.TemplateQuestionDefDao;
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
public class ExamTemplateService {

    @Autowired
    private ExamTemplateDao templateDao;
    @Autowired
    private MustChooseQuestionDefDao mustChooseDefDao;
    @Autowired
    private TemplateQuestionDefDao templateQuesDefDao;

    public void addExamTemplate(ExamTemplate template) {
        templateDao.save(template);
    }

    public ExamTemplate getExamTemplate(long tempId){
       return templateDao.findOne(tempId);
    }

    public List<ExamTemplate> getExamTemplatesByName(String name) {
        return templateDao.findByName(name);
    }

    public Page<ExamTemplate> getExamTemplatesByName(String name, int pageNum, int pageSize) {
        Pageable p = new PageRequest(pageNum, pageSize);
        return templateDao.findByName(name, p);
    }

    public List<ExamTemplate> getExamTemplateByNameLike(String nameLike) {
        return templateDao.findByNameLike(nameLike);
    }

    public Page<ExamTemplate> getExamTemplateByNameLike(String nameLike, int pageNum, int pageSize) {
        Pageable p = new PageRequest(pageNum, pageSize);
        return templateDao.findByNameLike(nameLike, p);
    }

    public List<MustChooseQuestionDef> getMustChooseDefs(long tempId){
        return mustChooseDefDao.findByTemplateId(tempId);
    }

    public void deleteExamTemplate(long id) {
        //没有使用CascadeType.REMOVE进行MustChooseQuestionDef和TemplateQuestionDef的
        //级联删除，原因是通过ExamTemplate级联删除时，每条def记录都会生成一条delete语句，如果def记录较多，则效率低
        mustChooseDefDao.deleteDefsForTemplate(id);
        templateQuesDefDao.deleteDefsForTemplate(id);
        templateDao.delete(id);
    }

}
