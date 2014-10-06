package org.dreamer.examination.service;

import org.dreamer.examination.business.ExaminationManager;
import org.dreamer.examination.entity.*;
import org.dreamer.examination.vo.ExamAndQuestionVO;
import org.dreamer.examination.vo.ExamQuestionVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

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
    @Autowired
    private QuestionService quesService;
    @Autowired
    private ExaminationService examService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private PaperService paperService;

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
    public void testConcurrentNewExamination() {
        int nt = 3;
        final int times = 10;
        Thread[] threads = new Thread[nt];
        for (int i = 0; i < nt; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < times; j++) {
                        Random r = new Random();
                        ExamAndQuestionVO vo = examManager.newExamination("480", "", 400);
                        Long paperId = vo.getPaperId();

                        List<Answer> answers = new ArrayList();
                        List<PaperQuestion> pqs = paperService.getPaperQuestions(paperId);
                        for(PaperQuestion q : pqs){
                            Answer a = new Answer();
                            a.setExamId(vo.getExamId());
                            a.setQuesId(q.getQuesId());
                            String ra = quesService.getQuestion(q.getQuesId()).getAnswer();
                            if(r.nextInt(100)>3){
                                a.setAnswer(ra);
                            }else{
                                a.setAnswer("W");
                            }
                            answers.add(a);
                        }
                        answerService.addAnswers(answers);
                        examService.scoreExam(vo.getExamId());
                    }
                }
            });
            threads[i] = t;
        }
        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("cost:" + (end - start) + " ms");
    }

    @Test
    public void testExamTemplate() {
        ExamTemplate template = templateService.getExamTemplate(50);
        Assert.assertEquals(10, template.getMustChooseDefs().size());
    }

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
        //schedule.setMajor("M001");
        schedule.setTemplate(template);
        schedule.setName("化学专业安全考试");

        scheduleService.addExamSchedule(schedule);
        Assert.assertNotNull(schedule.getId());
    }
}
