package org.dreamer.examination.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.dreamer.examination.entity.ExamSchedule;
import org.dreamer.examination.entity.ExamScheduleViewVO;
import org.dreamer.examination.entity.Types;
import org.dreamer.examination.service.ExamScheduleService;
import org.dreamer.examination.service.ExamTemplateService;
import org.dreamer.examination.sql.builder.SqlQueryModelBuilder;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.dreamer.examination.sql.model.SqlSortItem;
import org.dreamer.examination.sql.model.SqlSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考试查询管理控制器
 *
 * @author xwang
 * @version 1.0
 *          ${tags}
 */
@Controller
@RequestMapping(value = "/examschedule")
public class ExamScheduleController {

    @Autowired
    private ExamScheduleService examScheduleService;

    @Autowired
    private ExamTemplateService templateService;

    private static String[] majors = {"M001", "M002", "M003", "M004", "M005"};

    /**
     * 考试计划查询
     *
     * @param name
     * @param major
     * @param tempid
     * @param page
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView getScheduleList(@RequestParam(value = "name-li", required = false) String name,
                                        @RequestParam(value = "major-li", required = false, defaultValue = "") String major,
                                        @RequestParam(value = "tempid", required = false, defaultValue = "") Long tempid,
                                        @PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.examschedule-list");
        Page<ExamScheduleViewVO> examScheduleViewVOs = null;

        Map<String, Object> map = new HashMap<String, Object>();
        if (name != null) {
            map.put("name-li", name);
        }
        if (!StringUtils.isEmpty(major)) {
            map.put("major-li", major);
        }
        if (tempid != null) {
            map.put("tempid", tempid);
        }
        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);

        List<SqlSortItem> sortList = new ArrayList<SqlSortItem>();
        sortList.add(new SqlSortItem("id", SqlSortType.DESC));

        examScheduleViewVOs = examScheduleService.getScheduleByFilter(itemList, sortList, page);
        mv.addObject("schedulerecord", examScheduleViewVOs);
        mv.addObject("page", examScheduleViewVOs.getNumber() + 1);
        mv.addObject("totalPage", examScheduleViewVOs.getTotalPages());
        mv.addObject("templatelist", templateService.findAllTemplate());
        //搜索参数
        mv.addObject("name", name);
        mv.addObject("major", major);
        mv.addObject("tempid", tempid);
        return mv;
    }

    @RequestMapping(value = "/add")
    public String showAddPage(ModelMap map) {
        map.addAttribute("templatelist", templateService.findAllTemplate());
        map.addAttribute("majors", majors);
        return "exam.examschedule-add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addSchedule(ExamScheduleViewVO examScheduleViewVO) {
        ExamSchedule scheduleData = translateObject(examScheduleViewVO);
        if (scheduleData != null)
            examScheduleService.addExamSchedule(scheduleData);
        return "redirect:/examschedule/list";
    }


    @RequestMapping(value = "/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, ModelMap map) {
        ExamScheduleViewVO scheduleViewVO = null;
        if (id != null) {
            scheduleViewVO = examScheduleService.getScheduleViewVO(id);
        }
        if (scheduleViewVO == null) {
            scheduleViewVO = new ExamScheduleViewVO();
        }
        map.addAttribute("templatelist", templateService.findAllTemplate());
        map.addAttribute("majors", majors);
        map.addAttribute("schedule", scheduleViewVO);
        return "exam.examschedule-edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editSchedule(ExamScheduleViewVO examScheduleViewVO) {
        ExamSchedule scheduleData = translateObject(examScheduleViewVO);
        if (scheduleData != null)
            examScheduleService.addExamSchedule(scheduleData);
        return "redirect:/examschedule/list";
    }


    @RequestMapping(value = "/delete/{id}")
    public String deleteStore(@PathVariable("id") long id) {
        examScheduleService.delete(id);
        return "redirect:/examschedule/list";
    }

    private ExamSchedule translateObject(ExamScheduleViewVO examScheduleViewVO) {
        ExamSchedule scheduleData = null;
        if (examScheduleViewVO != null) {
            scheduleData = new ExamSchedule();
            scheduleData.setStartDate(examScheduleViewVO.getStartDate());
            scheduleData.setEndDate(examScheduleViewVO.getEndDate());
            scheduleData.setMajor(examScheduleViewVO.getMajor());
            scheduleData.setName(examScheduleViewVO.getName());
            scheduleData.setAdmissionYear(examScheduleViewVO.getAdmissionYear());

            if (examScheduleViewVO.getTempid() != null) {
                scheduleData.setTemplate(templateService.getExamTemplate(examScheduleViewVO.getTempid()));
            }
            if (examScheduleViewVO.getId() != null)
                scheduleData.setId(examScheduleViewVO.getId());
            examScheduleService.addExamSchedule(scheduleData);
            int dint = examScheduleViewVO.getDegree();
            if (dint == 0) {
                scheduleData.setDegree(Types.DegreeType.Bachelor);
            } else if (dint == 1) {
                scheduleData.setDegree(Types.DegreeType.Master);
            }
        }
        return scheduleData;
    }


}
