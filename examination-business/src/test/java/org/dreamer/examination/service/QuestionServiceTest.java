package org.dreamer.examination.service;

import org.dreamer.examination.entity.ChoiceQuestion;
import org.dreamer.examination.entity.Question;
import org.dreamer.examination.entity.QuestionOption;
import org.junit.Assert;
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
public class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;

    @Test
    public void testAddQuestion(){
        QuestionOption op1 = new QuestionOption("你说对吗？","A");
        QuestionOption op2 = new QuestionOption("你说对不对","C");
        QuestionOption op3 = new QuestionOption("你觉得对吗？","B");
        QuestionOption op4 = new QuestionOption("我不知道对不对？","D");
        List<QuestionOption> ops = new ArrayList<QuestionOption>();
        ops.add(op1);
        ops.add(op2);
        ops.add(op3);
        ops.add(op4);

        ChoiceQuestion question = new ChoiceQuestion();
        question.setMultiple(false);
        question.setAnswer("A");
        question.setStem("你任务对不对.");
        question.setQuestionOptions(ops);

        questionService.addQuestion(question);

        List<Question> qs = questionService.findAll();
        Assert.assertNotNull(qs);
        Assert.assertEquals(1, qs.size());
        Assert.assertNotNull(((ChoiceQuestion) qs.get(0)).getQuestionOptions());
        Assert.assertEquals(4, ((ChoiceQuestion) qs.get(0)).getQuestionOptions().size());
        Assert.assertEquals("B",((ChoiceQuestion)qs.get(0)).getQuestionOptions().get(1).getOrderNo());

        questionService.deleteQuestion(question);

        qs = questionService.findAll();
        Assert.assertEquals(0,qs.size());
    }
}
