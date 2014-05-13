package org.dreamer.examination.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.dreamer.examination.entity.*;
import org.dreamer.examination.service.ExamScheduleService;
import org.dreamer.examination.service.ExaminationService;
import org.dreamer.examination.service.ExaminationViewService;
import org.dreamer.examination.service.QuestionService;
import org.dreamer.examination.sql.builder.SqlQueryModelBuilder;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.dreamer.examination.utils.QuestionTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 考试查询管理控制器
 *
 * @author xwang
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/examquery")
public class ExamQueryController {

    @Autowired
    private ExaminationViewService examViewService;
    @Autowired
    private ExamScheduleService scheduleService;
    @Autowired
    private ExaminationService examService;
    @Autowired
    private QuestionService questionService;

    /**
     * 考试记录查询
     *
     * @param scheduleid  考试安排编号
     * @param examStaffId 考试人员编号
     * @param page        分页
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView getExamRecordList(@RequestParam(value = "scheduleid", required = false) Long scheduleid,
                                          @RequestParam(value = "examStaffId-li", required = false, defaultValue = "") String examStaffId,
                                          @PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.examquery-list");
        Page<ExaminationViewVO> examRecordVOs = null;

        Map<String, Object> map = new HashMap<String, Object>();
        if (scheduleid != null) {
            map.put("scheduleid", scheduleid);
        }
        if (!StringUtils.isEmpty(examStaffId)) {
            map.put("examStaffId-li", examStaffId);
        }
        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);
        examRecordVOs = examViewService.getExaminationByFilter(itemList, null, page);
        mv.addObject("examrecord", examRecordVOs);
        mv.addObject("page", examRecordVOs.getNumber() + 1);
        mv.addObject("totalPage", examRecordVOs.getTotalPages());
        mv.addObject("schedulelist", scheduleService.getAllSchedule());

        //搜索参数
        mv.addObject("scheduleid", scheduleid);
        mv.addObject("examStaffId", examStaffId);
        return mv;

    }

    /**
     * 未通过人员列表
     *
     * @param scheduleid  考试安排逐渐
     * @param examStaffId 人员编号
     * @param major       专业
     * @param page
     * @return
     */
    @RequestMapping(value = "/notpasslist")
    public ModelAndView getExamRecordNotPassList(@RequestParam(value = "scheduleid", required = false) Long scheduleid,
                                                 @RequestParam(value = "examStaffId-li", required = false, defaultValue = "") String examStaffId,
                                                 @RequestParam(value = "major-li", required = false, defaultValue = "") String major,
                                                 @PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.examquerynotpass-list");
        Page<ExaminationViewNotPassVO> examViewRecordVOs = null;

        Map<String, Object> map = new HashMap<String, Object>();
        if (scheduleid != null) {
            map.put("scheduleid", scheduleid);
        }
        if (!StringUtils.isEmpty(examStaffId)) {
            map.put("examStaffId-li", examStaffId);
        }
        if (!StringUtils.isEmpty(major)) {
            map.put("major-li", major);
        }
        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);
        examViewRecordVOs = examViewService.getExaminationNotPassByFilter(itemList, null, page);
        mv.addObject("examrecord", examViewRecordVOs);
        mv.addObject("page", examViewRecordVOs.getNumber() + 1);
        mv.addObject("totalPage", examViewRecordVOs.getTotalPages());
        mv.addObject("schedulelist", scheduleService.getAllSchedule());
        //搜索参数
        mv.addObject("scheduleid", scheduleid);
        mv.addObject("examStaffId", examStaffId);
        mv.addObject("major", major);
        return mv;
    }

    /**
     * 考试统计，通过率
     *
     * @param scheduleid 考试安排编号
     * @param major      专业
     * @param page
     * @return
     */
    @RequestMapping(value = "/passratelist")
    public ModelAndView getExamRecordPassrateList(@RequestParam(value = "scheduleid", required = false) Long scheduleid,
                                                  @RequestParam(value = "major-li", required = false, defaultValue = "") String major,
                                                  @PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.examquerypassrate-list");
        Page<ExaminationViewPassRateVO> examViewRecordVOs = null;

        Map<String, Object> map = new HashMap<String, Object>();
        if (scheduleid != null) {
            map.put("scheduleid", scheduleid);
        }
        if (!StringUtils.isEmpty(major)) {
            map.put("major-li", major);
        }
        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);
        examViewRecordVOs = examViewService.getExaminationPassRateByFilter(itemList, null, page);
        mv.addObject("examrecord", examViewRecordVOs);
        mv.addObject("page", examViewRecordVOs.getNumber() + 1);
        mv.addObject("totalPage", examViewRecordVOs.getTotalPages());
        mv.addObject("schedulelist", scheduleService.getAllSchedule());
        //搜索参数
        mv.addObject("scheduleid", scheduleid);
        mv.addObject("major", major);
        return mv;
    }

    @RequestMapping(value = "/paper")
    public ModelAndView examPaper(Long examId) {
        ModelAndView mv = new ModelAndView("exam.paper");
        Set<Types.QuestionType> types = examService.getExamQuestionDistinctType(examId);
        List<String[]> quesTypes = getQuestionTypeList(types);
        mv.addObject("quesTypes",quesTypes);
        List<Question> questions = questionService.getExamQuestion(examId,
                Types.QuestionType.getTypeFromShortName(quesTypes.get(0)[0]));
        mv.addObject("questions",questions);
        return mv;
    }

    private List<String[]> getQuestionTypeList(Set<Types.QuestionType> types) {
        List<String[]> result = new ArrayList<>();
        if (types.contains(Types.QuestionType.Choice)) {
            String[] a = new String[]{"CH", "单选题"};
            result.add(a);
        } else if (types.contains(Types.QuestionType.MultipleChoice)) {
            String[] a = new String[]{"MC", "多选题"};
            result.add(a);
        } else if (types.contains(Types.QuestionType.TrueFalse)){
            String[] a = new String[]{"TF", "判断题"};
            result.add(a);
        }
        return result;
    }
}
