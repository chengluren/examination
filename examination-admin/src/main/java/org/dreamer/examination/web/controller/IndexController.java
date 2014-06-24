package org.dreamer.examination.web.controller;

import org.dreamer.examination.rbac.ShiroDatabaseRealm;
import org.dreamer.examination.service.*;
import org.dreamer.examination.vo.ScheduleDateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    QuestionStoreService storeService;
    @Autowired
    QuestionService questionService;

    @Autowired
    ExamScheduleService scheduleService;

    @Autowired
    ExamTemplateService templateService;

    @Autowired
    ExaminationViewService examinationService;
    @Autowired
    private RBACService rbacService;

    @RequestMapping(value = "index")
    public ModelAndView index() {
//        Date now = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(now);
//        calendar.add(Calendar.MONTH, 1);
//        Date monthAfter = calendar.getTime();
        Date start = getFirstOfMonth();
        Date end = getLastDayOfMonth();

        List<ScheduleDateVO> scheduleList = getScheduleByDate(start,end);
        ModelAndView mv = new ModelAndView("exam.home");
        mv.addObject("store_count", storeService.getStoreCount());
        mv.addObject("question_count", questionService.getQuestionCount());
        mv.addObject("template_count", templateService.getTemplateCount());
        mv.addObject("schedule_count", scheduleList.size());

//        mv.addObject("paper_count", paperService.getPaperCount());
//        mv.addObject("average_passrate", examinationService.getAveragePassRate());
        return mv;
    }


    @RequestMapping(value = "getCalendarDate", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<ScheduleDateVO> getCurrentDate(@RequestParam("start") String start, @RequestParam("end") String end) {
        start = start + "000";
        end = end + "000";
        Date beginDate = new Date(Long.valueOf(start));
        Date endDate = new Date(Long.valueOf(end));
        return getScheduleByDate(beginDate,endDate);
    }

    private String getServerDate() {
        String dateStr = "";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateStr = sdf.format(dt);
        return dateStr;
    }

    public String transferMicroseconds2DateStr(Long millSecond) {
        Date date = new Date(millSecond);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    private Date getFirstOfMonth(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);
        return c.getTime();
    }

    private Date getLastDayOfMonth(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    private List<ScheduleDateVO> getScheduleByDate(Date beginDate,Date endDate){
        ShiroDatabaseRealm.ShiroUser user = rbacService.getCurrentUser();
        if (user != null) {
            Long collegeId = user.getCollegeId();
            if (collegeId != null && collegeId != -1) {
                return scheduleService.getCollegeScheduleByDate(collegeId, beginDate, endDate);
            } else {
                return scheduleService.getScheduleDataByData(beginDate, endDate);
            }
        } else {
            return scheduleService.getScheduleDataByData(beginDate, endDate);
        }
    }
}
