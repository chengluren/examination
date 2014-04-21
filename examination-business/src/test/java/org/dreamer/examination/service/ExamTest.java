package org.dreamer.examination.service;

import org.dreamer.examination.business.ExaminationManager;
import org.dreamer.examination.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ExamTest {
    @Autowired
    private ExamTemplateService templateService;
    @Autowired
    private ExamScheduleService scheduleService;
    @Autowired
    private ExaminationManager examManager;

//    @Test
//    public void testTakeExam() {
//        ExamAndQuestionVO vo = examManager.newExamination("001", "M001",1);
//        System.out.println(vo.getExamId());
//        System.out.println(vo.getPaperId());
//        List<ExamQuestionVO> qvo = vo.getQuestions();
//        for (ExamQuestionVO o : qvo){
//            System.out.println(o.getQuesId()+"|"+o.getScore()+"|"+o.getStem());
//        }
//
//        qvo = examManager.getPaperQuestions(vo.getExamId(), Types.QuestionType.TrueFalse);
//        for (ExamQuestionVO o : qvo){
//            System.out.println(o.getQuesId()+"|"+o.getScore()+"|"+o.getStem());
//        }
//    }

//    @Test
//    public void testExamTemplateCrud() {
//        ExamTemplate template = new ExamTemplate();
//        template.setName("生物专业实验室安全考试");
//
//        TemplateQuestionDef def1 = new TemplateQuestionDef(4, Types.QuestionType.Choice, 20, 1);
//        TemplateQuestionDef def2 = new TemplateQuestionDef(15, Types.QuestionType.Choice, 30, 1);
//        TemplateQuestionDef def3 = new TemplateQuestionDef(4, Types.QuestionType.TrueFalse, 20, 1);
//        TemplateQuestionDef def4 = new TemplateQuestionDef(15, Types.QuestionType.TrueFalse, 30, 1);
//
//        def1.setTemplate(template);
//        def2.setTemplate(template);
//        def3.setTemplate(template);
//        def4.setTemplate(template);
//
//        List<TemplateQuestionDef> defs = new ArrayList<>();
//        defs.add(def1);
//        defs.add(def2);
//        defs.add(def3);
//        defs.add(def4);
//
//        template.setQuestionDefs(defs);
//        template.setMultiChoiceMixedInChoice(false);
//        template.setPassScore(60);
//
//        templateService.addExamTemplate(template);
//
//        Long id = template.getId();
//        Assert.assertNotNull(id);
//
//        List<String> types = templateService.getDistinctQuesTypes(id);
//        Assert.assertEquals(2, types.size());
//        Assert.assertEquals("CH", types.get(0));
//        Assert.assertEquals("TF", types.get(1));
//    }

    @Test
    public void testExamScheduleCrud() {

        ExamTemplate template = templateService.getExamTemplate(1);
        ExamSchedule schedule = new ExamSchedule();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MONTH, 3);

        Date endDate = cal.getTime();

        schedule.setStartDate(now);
        schedule.setEndDate(endDate);
        schedule.setMajor("M001");
        schedule.setTemplate(template);
        schedule.setName("化学专业安全考试");

        scheduleService.addExamSchedule(schedule);
        Assert.assertNotNull(schedule.getId());
    }
}
