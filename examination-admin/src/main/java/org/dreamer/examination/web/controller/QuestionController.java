package org.dreamer.examination.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.dreamer.examination.entity.*;
import org.dreamer.examination.service.QuestionService;
import org.dreamer.examination.service.QuestionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcheng on 2014/4/22.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService quesService;
    @Autowired
    private QuestionStoreService storeService;

    @RequestMapping("/list")
    public ModelAndView questionList(Long storeId, String quesType, @PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.question-list");
        List<QuestionStore> stores = storeService.getAll();
        if (storeId == null && (stores != null && stores.size() > 0)) {
            storeId = stores.get(0).getId();
        }
        if (quesType == null) {
            quesType = "CH";
        }
        if (storeId != null) {
            Page<Question> questions = quesService.getQuestions(storeId,
                    Types.QuestionType.getTypeFromShortName(quesType), page);
            mv.addObject("questions", questions.getContent());
            mv.addObject("storeId", storeId);
            mv.addObject("quesType", quesType);
            mv.addObject("page", questions.getNumber() + 1);
            mv.addObject("totalPage", questions.getTotalPages());
        }
        mv.addObject("stores", stores);
        return mv;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> questionList(String sEcho, int iDisplayStart,
                                            int iDisplayLength, long storeId, String quesType) {
        Map<String, Object> result = new HashMap<>();
        result.put("sEcho", sEcho);
        result.put("iTotalRecords", 20);
        result.put("iTotalDisplayRecords", 20);
        result.put("aaData", new String[][]{{"1", "测试题目1"}, {"2", "测试题目2"}});
        return result;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editQuestion(@PathVariable("id") Long id) {
        Question question = quesService.getQuestion(id);
        ModelAndView mv = new ModelAndView("exam.question-edit");
        mv.addObject("q", question);
        return mv;
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Result editQuestion(String question){
        Result result = new Result(true,"");
        ObjectMapper mapper = new ObjectMapper();
//        try {
////            mapper.readValue(question, TypeFactory.defaultInstance().cons)
////            List<Answer> answerList = mapper.readValue(question,
////                    TypeFactory.defaultInstance().constructCollectionType(List.class, QuestionVO.class));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return result;
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id, Long storeId, String quesType, int page, int size) {
        quesService.deleteQuestion(id);
        String redUrl = "/question/list?storeId=" + storeId + "&quesType=" + quesType + "&page=" + page + "&size=" + size;
        return "redirect:" + redUrl;
    }

    @RequestMapping(value = "/option/question/{id}")
    @ResponseBody
    public Result deleteQuestionOption(@PathVariable("id") Long id) {
        Result result = null;
        try {
            quesService.deleteQuestionOption(id);
            result = new Result(true,"删除选项成功!");
        }catch (Exception e){
            result = new Result(false,"删除选项失败!");
        }
        return result;
    }
}
