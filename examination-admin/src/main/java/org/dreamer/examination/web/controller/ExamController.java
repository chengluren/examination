package org.dreamer.examination.web.controller;

import org.codehaus.jackson.map.util.JSONPObject;
import org.dreamer.examination.business.ExaminationManager;
import org.dreamer.examination.entity.CommitAnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 考试控制器
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/exam")
public class ExamController {

    @Autowired
    private ExaminationManager examManager;

    /**
     * <p>学生参加考试。根据学生专业到后台匹配考试模板，
     * 匹配到模板后,为学生返回试题列表，考试Id、试卷号</p>
     */
    @RequestMapping(value = "/newExam")
    @ResponseBody
    public JSONPObject takeExamination(String staffId,String major){

        return null;
    }

    /**
     * <p>学生提交答案，返回最终得分。此为一次性提交所有答案</p>
     * <p>提交的答案内容格式为：
     * {
     *  examId:1
     *  paperId:2
     *  answers:{
     *      1111: A
     *      2222:B
     *  }
     * }</p>
     * @param answer
     * @return
     */
    @RequestMapping(value = "/commitAnswer")
    @ResponseBody
    public JSONPObject commitAnswer(CommitAnswerVO answer){

       return null;
    }

    /**
     * 按题型提交答案
     * @param answer
     * @param type
     * @return
     */
    @RequestMapping(value = "/commitAnswer/{type}")
    public JSONPObject commitAnswer(@PathVariable("type")String type,CommitAnswerVO answer){
      return null;
    }

    //public JSONPObject
}
