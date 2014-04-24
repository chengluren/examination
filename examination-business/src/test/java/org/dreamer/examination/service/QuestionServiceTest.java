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
 * @version 1.0 ${tags}
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;

    @Test
    public void testAddQuestion() {
        QuestionOption op1 = new QuestionOption("你说对吗？", "A");
        QuestionOption op2 = new QuestionOption("你说对不对", "C");
        QuestionOption op3 = new QuestionOption("你觉得对吗？", "B");
        QuestionOption op4 = new QuestionOption("我不知道对不对？", "D");
        List<QuestionOption> ops = new ArrayList<QuestionOption>();
        ops.add(op1);
        ops.add(op2);
        ops.add(op3);
        ops.add(op4);

        ChoiceQuestion question = new ChoiceQuestion();
        // question.setMultiple(false);
        question.setAnswer("A");
        question.setStem("你任务对不对.");
        question.setQuestionOptions(ops);
        question.setStoreId(3L);

        questionService.addQuestion(question);

        ChoiceQuestion queried = (ChoiceQuestion) questionService.getQuestion(question.getId());
        Assert.assertNotNull(queried.getQuestionOptions());

        questionService.deleteQuestionOption(queried.getQuestionOptions().get(0).getId());

        QuestionOption qo = new QuestionOption("测试Option","A");
        List<QuestionOption> exsist = queried.getQuestionOptions();
        exsist.remove(0);
        List<QuestionOption> newOpList = new ArrayList<>();
        newOpList.addAll(exsist);
        newOpList.add(qo);
        queried.setQuestionOptions(newOpList);

        questionService.addQuestion(queried);

//        questionService.updateQuestion(queried);

        queried = (ChoiceQuestion) questionService.getQuestion(queried.getId());
        Assert.assertEquals(4,queried.getQuestionOptions().size());

        questionService.deleteQuestion(queried.getId());
    }
}
