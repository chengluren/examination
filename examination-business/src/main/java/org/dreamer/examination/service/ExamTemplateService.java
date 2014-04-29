package org.dreamer.examination.service;

import org.dreamer.examination.entity.ExamTemplate;
import org.dreamer.examination.entity.MustChooseQuestionDef;
import org.dreamer.examination.entity.TemplateQuestionDef;
import org.dreamer.examination.entity.Types;
import org.dreamer.examination.repository.ExamTemplateDao;
import org.dreamer.examination.repository.MustChooseQuestionDefDao;
import org.dreamer.examination.repository.TemplateQuestionDefDao;
import org.dreamer.examination.utils.QuestionTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    public void addExamTempQuesDef(TemplateQuestionDef def, Long tempId) {
        ExamTemplate template = templateDao.getOne(tempId);
        def.setTemplate(template);
        templateQuesDefDao.save(def);
    }

    public void addMustChooseDef(MustChooseQuestionDef def, Long tempId) {
        ExamTemplate template = templateDao.getOne(tempId);
        def.setTemplate(template);
        mustChooseDefDao.save(def);
    }

    public void addMustChooseDefs(List<MustChooseQuestionDef> defs, Long tempId) {
        ExamTemplate template = templateDao.getOne(tempId);
        for (MustChooseQuestionDef def : defs) {
            def.setTemplate(template);
        }
        mustChooseDefDao.save(defs);
    }

    public ExamTemplate getExamTemplate(long tempId) {
        return templateDao.findOne(tempId);
    }

    public Page<ExamTemplate> getExamTemplate(Pageable page) {
        return templateDao.findAll(page);
    }

    public List<ExamTemplate> getExamTemplatesByName(String name) {
        return templateDao.findByName(name);
    }

    public Page<ExamTemplate> getExamTemplatesByName(String name, Pageable page) {
        return templateDao.findByName(name, page);
    }

    public List<ExamTemplate> getExamTemplateByNameLike(String nameLike) {
        return templateDao.findByNameLike(nameLike);
    }

    public Page<ExamTemplate> getExamTemplateByNameLike(String nameLike, Pageable page) {
        return templateDao.findByNameLike(nameLike, page);
    }

    public List<Object[]> getExamTemplateInfo(Long tempId) {
        return templateDao.findTemplateBaseInfo(tempId);
    }

    public Page<List<Object[]>> getExamTemplateMustChooseDef(Long tempId, Pageable pageable) {
        return mustChooseDefDao.findMustChooseDefs(tempId, pageable);
    }

    public List<Object[]> getExamTemplateQuesDef(Long tempId) {
        return templateQuesDefDao.findTemplateQuestionDefs(tempId);
    }

    public List<MustChooseQuestionDef> getMustChooseDefs(long tempId) {
        return mustChooseDefDao.findByTemplateId(tempId);
    }

    public List<String> getDistinctQuesTypes(long tempId) {
        ExamTemplate template = templateDao.getOne(tempId);
        Set<Types.QuestionType> tempTypes = templateQuesDefDao.findDistinctQuesTypes(tempId);
        Set<Types.QuestionType> mustTypes = mustChooseDefDao.findDistinctQuesTypes(tempId);
        tempTypes.addAll(mustTypes);

        boolean multiMixed = template.isMultiChoiceMixedInChoice();
        List<String> result = QuestionTypeUtils.getOrderedType(tempTypes, multiMixed);
        return result;
    }

    public void updateTemplate(Long tempId,String name,float passScore,boolean mixedIn){
        templateDao.updateTemplate(tempId,name,passScore,mixedIn);
    }

    public void deleteExamTemplate(long id) {
        //没有使用CascadeType.REMOVE进行MustChooseQuestionDef和TemplateQuestionDef的
        //级联删除，原因是通过ExamTemplate级联删除时，每条def记录都会生成一条delete语句，如果def记录较多，则效率低
        mustChooseDefDao.deleteDefsForTemplate(id);
        templateQuesDefDao.deleteDefs(id);
        templateDao.delete(id);
    }

    public void deleteTemplateMustChoose(long mustChooseId) {
        MustChooseQuestionDef def = mustChooseDefDao.findOne(mustChooseId);
        def.getTemplate().getMustChooseDefs().remove(def);
        mustChooseDefDao.delete(def);
        mustChooseDefDao.flush();
    }

    public void deleteTemplateQuestionDef(long tempQuesId) {
        TemplateQuestionDef def = templateQuesDefDao.findOne(tempQuesId);
        def.getTemplate().getQuestionDefs().remove(def);
        templateQuesDefDao.delete(tempQuesId);
        templateQuesDefDao.flush();
    }

    public List<ExamTemplate> findAllTemplate() {
        return templateDao.findAll();
    }

    public Long getTemplateCount()
    {
        return templateDao.getTemplateCount() == null ? 0L :templateDao.getTemplateCount();
    }
}
