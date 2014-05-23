package org.dreamer.examination.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.dreamer.examination.business.ExamNotPassExcelCreator;
import org.dreamer.examination.business.ExamRecordExcelCreator;
import org.dreamer.examination.business.ExcelCreator;
import org.dreamer.examination.business.ExcelSettings;
import org.dreamer.examination.entity.*;
import org.dreamer.examination.service.ExamScheduleService;
import org.dreamer.examination.service.ExaminationService;
import org.dreamer.examination.service.ExaminationViewService;
import org.dreamer.examination.service.QuestionService;
import org.dreamer.examination.sql.builder.SqlQueryModelBuilder;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.dreamer.examination.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
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
     * @param viewVO
     * @param page
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView getExamRecordList(ExaminationViewVO viewVO, @PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.examquery-list");
        Page<ExaminationViewVO> examRecordVOs = null;

        Map<String, Object> map = new HashMap<>();
        if (viewVO.getScheduleid() != null) {
            map.put("scheduleid", viewVO.getScheduleid());
            ExamSchedule schedule = scheduleService.getExamSchedule(viewVO.getScheduleid());
            mv.addObject("scheduleName",schedule.getName());
            mv.addObject("scheduleid",viewVO.getScheduleid());
        }
        if (StringUtils.isNotEmpty(viewVO.getMajorName())) {
            map.put("majorName-li", viewVO.getMajorName());
        }
        if (StringUtils.isNotEmpty(viewVO.getClassName())) {
            map.put("className", viewVO.getClassName());
        }
        if (StringUtils.isNotEmpty(viewVO.getStuNo())) {
            map.put("stuNo", viewVO.getStuNo());
        }
        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);
        examRecordVOs = examViewService.getExaminationByFilter(itemList, null, page);
        mv.addObject("examrecord", examRecordVOs);
        mv.addObject("page", examRecordVOs.getNumber() + 1);
        mv.addObject("totalPage", examRecordVOs.getTotalPages());
        mv.addObject("totalCount", examRecordVOs.getTotalElements());

        mv.addObject("schedulelist", scheduleService.getAllSchedule());

        if (viewVO == null) {
            viewVO = new ExaminationViewVO();
        }
        mv.addObject("query", viewVO);
        return mv;
    }

    @RequestMapping(value = "/examRecordDownload")
    public void downloadExamRecord(ExaminationViewVO vo, HttpServletResponse response) {
        ExcelCreator creator = new ExamRecordExcelCreator(examViewService, vo);
        String title = "";
        if (vo != null && StringUtils.isNotEmpty(vo.getSchedulename())) {
            title = vo.getSchedulename() + "学生考试记录表";
        } else {
            title = "学生考试记录表";
        }
        ExcelSettings settings = new ExcelSettings(title, Constants.EXAM_RECORD_COLUMNS);
        try {
            response.reset();
            String fileName = title+".xlsx";
            fileName = new String(fileName.getBytes(),"ISO8859-1");
            response.addHeader("Content-Disposition","attachment; filename="+fileName );
            response.setContentType("application/octet-stream");
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            creator.createExcel(settings, ((ExamRecordExcelCreator) creator).new ExamRecordDataProvider(), bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 未通过人员列表
     *
     * @param viewVO
     * @param page
     * @return
     */
    @RequestMapping(value = "/notpasslist")
    public ModelAndView getExamRecordNotPassList(ExaminationViewNotPassVO viewVO, @PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.examquerynotpass-list");
        Page<ExaminationViewNotPassVO> examViewRecordVOs = null;

        Map<String, Object> map = new HashMap<>();
        if (viewVO.getScheduleid() != null) {
            map.put("scheduleid", viewVO.getScheduleid());
        }
        if (StringUtils.isNotEmpty(viewVO.getMajorName())) {
            map.put("majorName-li", viewVO.getMajorName());
        }
        if (StringUtils.isNotEmpty(viewVO.getClassName())) {
            map.put("className", viewVO.getClassName());
        }
        if (StringUtils.isNotEmpty(viewVO.getStuNo())) {
            map.put("stuNo", viewVO.getStuNo());
        }
        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);
        examViewRecordVOs = examViewService.getExaminationNotPassByFilter(itemList, null, page);
        mv.addObject("examrecord", examViewRecordVOs);
        mv.addObject("page", examViewRecordVOs.getNumber() + 1);
        mv.addObject("totalPage", examViewRecordVOs.getTotalPages());
        mv.addObject("totalCount", examViewRecordVOs.getTotalElements());
        mv.addObject("schedulelist", scheduleService.getAllSchedule());
        //搜索参数
        if (viewVO == null) {
            viewVO = new ExaminationViewNotPassVO();
        }
        mv.addObject("query", viewVO);
        return mv;
    }

    @RequestMapping(value = "/examNotPassDownload")
    public void downloadExamNotPass(ExaminationViewNotPassVO vo, HttpServletResponse response) {
        ExcelCreator creator = new ExamNotPassExcelCreator(examViewService, vo);
        String title = "";
        if (vo != null && StringUtils.isNotEmpty(vo.getSchedulename())) {
            title = vo.getSchedulename() + "考试未通过记录表";
        } else {
            title = "考试未通过记录表";
        }
        ExcelSettings settings = new ExcelSettings(title, Constants.EXAM_RECORD_COLUMNS);
        try {
            response.reset();
            String fileName = title+".xlsx";
            fileName = new String(fileName.getBytes(),"ISO8859-1");
            response.addHeader("Content-Disposition","attachment; filename="+fileName );
            response.setContentType("application/octet-stream");
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            creator.createExcel(settings, ((ExamNotPassExcelCreator) creator).new ExamNotPassDataProvider(), bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 考试统计，通过率
     *
     * @param scheduleid 考试安排编号
     * @param className   班级
     * @param page
     * @return
     */
    @RequestMapping(value = "/passratelist")
    public ModelAndView getExamRecordPassrateList(Long scheduleid, String className,@PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.examquerypassrate-list");
        Page<ExaminationViewPassRateVO> examViewRecordVOs = null;

        Map<String, Object> map = new HashMap<String, Object>();
        if (scheduleid != null) {
            map.put("scheduleid", scheduleid);
            ExamSchedule schedule = scheduleService.getExamSchedule(scheduleid);
            mv.addObject("scheduleName",schedule.getName());
            mv.addObject("scheduleid",scheduleid);
        }
        if (!StringUtils.isEmpty(className)) {
            map.put("className-li", className);
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
        mv.addObject("className", className);
        return mv;
    }

    @RequestMapping(value = "/paper")
    public ModelAndView examPaper(Long examId) {
        ModelAndView mv = new ModelAndView("exam.paper");
        Set<Types.QuestionType> types = examService.getExamQuestionDistinctType(examId);
        List<String[]> quesTypes = getQuestionTypeList(types);
        mv.addObject("quesTypes", quesTypes);
        List<Question> questions = questionService.getExamQuestion(examId,
                Types.QuestionType.getTypeFromShortName(quesTypes.get(0)[0]));
        Map<String, Object> map = new HashMap<>();
        map.put("id", examId);
        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);
        Page<ExaminationViewVO> data = examViewService.getExaminationByFilter(itemList, null, null);
        mv.addObject("questions", questions);
        mv.addObject("examId", examId);
        mv.addObject("examVO", data.getContent().get(0));
        return mv;
    }

    @RequestMapping(value = "/paper/questions")
    @ResponseBody
    public List<Question> examPaperQuestions(Long examId, String quesType) {
        return questionService.getExamQuestion(examId,
                Types.QuestionType.getTypeFromShortName(quesType));
    }

    @RequestMapping(value = "/paper/answers")
    @ResponseBody
    public List<Object[]> examPaperAnswers(Long examId, String quesType) {
        return examService.getExamQuestionAnswers(examId,
                Types.QuestionType.getTypeFromShortName(quesType));
    }

    @RequestMapping(value = "/paper/quesScores")
    @ResponseBody
    public List<Object[]> examPaperQuestionScore(Long examId, String quesType) {
        return examService.getExamQuestionScores(examId,
                Types.QuestionType.getTypeFromShortName(quesType));
    }

    private List<String[]> getQuestionTypeList(Set<Types.QuestionType> types) {
        List<String[]> result = new ArrayList<>();
        if (types.contains(Types.QuestionType.Choice)) {
            String[] a = new String[]{"CH", "单选题"};
            result.add(a);
        }
        if (types.contains(Types.QuestionType.MultipleChoice)) {
            String[] a = new String[]{"MC", "多选题"};
            result.add(a);
        }
        if (types.contains(Types.QuestionType.TrueFalse)) {
            String[] a = new String[]{"TF", "判断题"};
            result.add(a);
        }
        return result;
    }
}
