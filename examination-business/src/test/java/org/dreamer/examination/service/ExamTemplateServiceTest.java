package org.dreamer.examination.service;

import org.dreamer.examination.entity.ExamTemplate;
import org.dreamer.examination.entity.MustChooseQuestionDef;
import org.dreamer.examination.entity.TemplateQuestionDef;
import org.dreamer.examination.entity.Types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ExamTemplateServiceTest {

    @Autowired
    private ExamTemplateService templateService;

    @Test
    public void testAdd(){
        ExamTemplate template = new ExamTemplate();
        template.setName("test");
        template.setPassScore(60);

        MustChooseQuestionDef d1 = new MustChooseQuestionDef();
        d1.setQuestionId(100);
        d1.setQuestionType(Types.QuestionType.Choice);
        d1.setQuesScore(1);
        d1.setTemplate(template);

        MustChooseQuestionDef d2 = new MustChooseQuestionDef();
        d2.setQuestionId(101);
        d2.setQuestionType(Types.QuestionType.Choice);
        d2.setQuesScore(1);
        d2.setTemplate(template);

        List<MustChooseQuestionDef> mcd = new ArrayList<>();
        mcd.add(d1);
        mcd.add(d2);
        template.setMustChooseDefs(mcd);

        TemplateQuestionDef qd1 = new TemplateQuestionDef(1000, Types.QuestionType.Choice,20,1);
        TemplateQuestionDef qd2 = new TemplateQuestionDef(1001, Types.QuestionType.TrueFalse,20,1);
        qd1.setTemplate(template);
        qd2.setTemplate(template);
        List<TemplateQuestionDef> tqd = new ArrayList<>();
        tqd.add(qd1);
        tqd.add(qd2);
        template.setQuestionDefs(tqd);

        templateService.addExamTemplate(template);
        List<ExamTemplate> findedTemp = templateService.getExamTemplatesByName("test");
        assertEquals(1,findedTemp.size());
        assertEquals(2, findedTemp.get(0).getMustChooseDefs().size());

        List<MustChooseQuestionDef> mcqds = templateService.getMustChooseDefs(template.getId());
        assertNotNull(mcqds);
        assertEquals(2,mcqds.size());

        templateService.deleteExamTemplate(template.getId());
    }
}
