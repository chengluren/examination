package org.dreamer.examination.web.controller;

import org.dreamer.examination.entity.QuestionStore;
import org.dreamer.examination.service.QuestionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/template")
public class ExamTemplateController {

    @Autowired
    private QuestionStoreService storeService;

    @RequestMapping(value = "/new")
    public ModelAndView newExamTemplate(){
        ModelAndView mv = new ModelAndView("exam.temp-new");
        List<QuestionStore> stores =  storeService.getAll();
        mv.addObject("stores",stores);
        return mv;
    }
}
