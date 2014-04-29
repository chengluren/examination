package org.dreamer.examination.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.dreamer.examination.entity.ScheduleDateVO;
import org.dreamer.examination.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    @Autowired
    PaperService paperService;

    @Autowired
    QuestionService questionService;

    @Autowired
    ExamScheduleService scheduleService;

    @Autowired
    ExamTemplateService templateService;

    @Autowired
    ExaminationViewService examinationService;

    @RequestMapping(value = "index")
    public ModelAndView index( ){
        ModelAndView mv = new ModelAndView("exam.home");
        mv.addObject("papter_count",paperService.getPaperCount() );
        mv.addObject("question_count",questionService.getQuestionCount() );
        mv.addObject("average_passrate",examinationService.getAveragePassRate() );
        mv.addObject("template_count",templateService.getTemplateCount() );
        return mv;
    }


    @RequestMapping(value = "getCalendarDate",method = {RequestMethod.GET,RequestMethod.POST} )
    @ResponseBody
    public List<ScheduleDateVO> getCurrentDate( @RequestParam("start") String start , @RequestParam("end")String end ){
        start = start + "000";
        end = end + "000";
      //  System.out.println( transferMicroseconds2DateStr(Long.valueOf(start)) );
      // System.out.println( transferMicroseconds2DateStr(Long.valueOf(end)) );
        Date beginDate = new Date( Long.valueOf(start ));
        Date endDate = new Date( Long.valueOf(end ));
        List<ScheduleDateVO> scheduleList = scheduleService.getScheduleDataByData( beginDate , endDate );
        return scheduleList;
    }
   /*
    @RequestMapping(value = "getCalenDarDate",method = {RequestMethod.GET,RequestMethod.POST} )
    @ResponseBody
    public String getCurrentDate( HttpServletRequest _request ){
        Enumeration<String> keySet = _request.getParameterNames();
        while ( keySet.hasMoreElements() )
        {
            String key = keySet.nextElement();
            Object val = _request.getParameter(key);
            System.out.println( "key:" + key + "==value:" + val );
        }

        return "";
    }
    */

    private String getServerDate()
    {
        String dateStr = "";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateStr=sdf.format(dt);
        return dateStr;
    }

    public String transferMicroseconds2DateStr( Long millSecond )
    {
        Date date = new Date(millSecond);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }


}
