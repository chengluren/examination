package org.dreamer.examination.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcheng on 2014/4/22.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    @RequestMapping("/list")
    public String questionList(){
        return "exam.question-list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> questionList(String sEcho,int iDisplayStart,
                                     int iDisplayLength,long storeId,String quesType){
        Map<String,Object> result = new HashMap<>();
        result.put("sEcho",sEcho);
        result.put("iTotalRecords",20);
        result.put("iTotalDisplayRecords",20);
        result.put("aaData",new String[][]{{"1","测试题目1"},{"2","测试题目2"}});
        return result;
    }
}
