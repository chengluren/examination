package org.dreamer.examination.web.controller;

import org.codehaus.jackson.map.util.JSONPObject;
import org.dreamer.examination.business.ExaminationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * 学生参加考试
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public JSONPObject takeExamination(String staffId,String major,long tempId){
        //examManager.newExamination(tempId,staffId,)
        return null;
    }
}
