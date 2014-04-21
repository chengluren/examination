package org.dreamer.examination.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.dreamer.examination.business.DefaultRandomStrategy;
import org.dreamer.examination.business.RandomStrategy;
import org.dreamer.examination.entity.Question;
import org.dreamer.examination.entity.Types;
import org.dreamer.examination.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/train")
public class TrainingController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/sequenceLoad")
    @ResponseBody
    public JSONPObject sequenceLoad(long storeId, String type, Pageable page, String callback) {
        Types.QuestionType quesType = Types.QuestionType.getTypeFromShortName(type);
        Page<Question> ques = questionService.getQuestions(storeId, quesType, page);
        JSONPObject result = new JSONPObject(callback, ques);
        return result;
    }

    @RequestMapping(value = "/randomLoad")
    @ResponseBody
    public JSONPObject randomLoad(long storeId, String type, int count, String callback) {
        RandomStrategy random = new DefaultRandomStrategy(questionService);
        List<Question> questions = random.randomLoad(storeId, type, count);
        JSONPObject jsonp = new JSONPObject(callback, questions);
        return jsonp;
    }
}
