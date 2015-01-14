package org.dreamer.examination.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.dreamer.examination.business.*;
import org.dreamer.examination.entity.*;
import org.dreamer.examination.rbac.ShiroDatabaseRealm;
import org.dreamer.examination.service.*;
import org.dreamer.examination.sql.builder.SqlQueryModelBuilder;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.dreamer.examination.utils.Constants;
import org.dreamer.examination.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    @Autowired
    private RBACService rbacService;

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
            mv.addObject("scheduleName", schedule.getName());
            mv.addObject("scheduleid", viewVO.getScheduleid());
        }
        if (StringUtils.isNotEmpty(viewVO.getMajorName())) {
            map.put("majorName-li", viewVO.getMajorName());
        }
        if (StringUtils.isNotEmpty(viewVO.getClassName())) {
            map.put("className-li", viewVO.getClassName());
        }
        if (StringUtils.isNotEmpty(viewVO.getCollege())) {
            map.put("college-li", viewVO.getCollege());
        }
        if (StringUtils.isNotEmpty(viewVO.getStuNo())) {
            map.put("stuNo-li", viewVO.getStuNo());
        }
        setCollegeIdMap(map);

        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);
        examRecordVOs = examViewService.getExaminationByFilter(itemList, null, page);
        mv.addObject("examrecord", examRecordVOs);
        mv.addObject("page", examRecordVOs.getNumber() + 1);
        mv.addObject("totalPage", examRecordVOs.getTotalPages());
        mv.addObject("totalCount", examRecordVOs.getTotalElements());
        //mv.addObject("schedulelist", scheduleService.getAllSchedule());

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
        setCollegeId(vo);
        ExcelSettings settings = new ExcelSettings(title, Constants.EXAM_RECORD_COLUMNS);
        try {
            response.reset();
            String fileName = title + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream");
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            creator.createExcel(settings, ((ExamRecordExcelCreator) creator).new ExamRecordDataProvider(), bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/passlist")
    public ModelAndView getExamRecordPassList(ExaminationViewPassVO viewVO, @PageableDefault Pageable page) {
        ModelAndView mv = new ModelAndView("exam.examquerypass-list");
        Page<ExaminationViewPassVO> examViewRecordVOs = null;
        if (viewVO.getPromise() == null) {
            viewVO.setPromise(-1);
        }

        Map<String, Object> map = new HashMap<>();
        if (viewVO.getScheduleid() != null) {
            map.put("scheduleid", viewVO.getScheduleid());
            ExamSchedule schedule = scheduleService.getExamSchedule(viewVO.getScheduleid());
            mv.addObject("scheduleName", schedule.getName());
            mv.addObject("scheduleid", viewVO.getScheduleid());
        }
        if (StringUtils.isNotEmpty(viewVO.getMajorName())) {
            map.put("majorName-li", viewVO.getMajorName());
        }
        if (StringUtils.isNotEmpty(viewVO.getClassName())) {
            map.put("className-li", viewVO.getClassName());
        }
        if (StringUtils.isNotEmpty(viewVO.getCollege())) {
            map.put("college-li", viewVO.getCollege());
        }
        if (StringUtils.isNotEmpty(viewVO.getStuNo())) {
            map.put("stuNo-li", viewVO.getStuNo());
        }
        if (viewVO.getPromise() != null && viewVO.getPromise() != -1) {
            map.put("promise", viewVO.getPromise());
        }
        setCollegeIdMap(map);

        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);
        examViewRecordVOs = examViewService.getExaminationPassByFilter(itemList, null, page);
        mv.addObject("examrecord", examViewRecordVOs);
        mv.addObject("page", examViewRecordVOs.getNumber() + 1);
        mv.addObject("totalPage", examViewRecordVOs.getTotalPages());
        mv.addObject("totalCount", examViewRecordVOs.getTotalElements());
        //搜索参数
        if (viewVO == null) {
            viewVO = new ExaminationViewPassVO();
        }
        mv.addObject("query", viewVO);
        return mv;
    }

    @RequestMapping(value = "/examPassDownload")
    public void downloadExamPass(ExaminationViewPassVO vo, HttpServletResponse response) {
        ExcelCreator creator = new ExamPassExcelCreator(examViewService, vo);
        String title = "";
        if (vo != null && StringUtils.isNotEmpty(vo.getSchedulename())) {
            title = vo.getSchedulename() + "考试通过记录表";
        } else {
            title = "考试通过记录表";
        }
        setCollegeId(vo);
        ExcelSettings settings = new ExcelSettings(title, Constants.EXAM_RECORD_COLUMNS);
        try {
            response.reset();
            String fileName = title + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream");
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            creator.createExcel(settings, ((ExamPassExcelCreator) creator).new ExamPassDataProvider(), bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/passCertificateDownload")
    public void downloadCertificate(ExaminationViewPassVO vo, HttpServletResponse response) {
        String title = "";
        if (vo != null && StringUtils.isNotEmpty(vo.getSchedulename())) {
            title = vo.getSchedulename() + "考试合格证书";
        } else {
            title = "考试合格证书";
        }
        setCollegeId(vo);
        try {
            response.reset();
            String fileName = title + ".docx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream");
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

            CertificateCreator creator = new CertificateCreator(examViewService, vo);
            XWPFDocument word = creator.create();
            word.write(bos);
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
            ExamSchedule schedule = scheduleService.getExamSchedule(viewVO.getScheduleid());
            mv.addObject("scheduleName", schedule.getName());
            mv.addObject("scheduleid", viewVO.getScheduleid());
        }
        if (StringUtils.isNotEmpty(viewVO.getMajorName())) {
            map.put("majorName-li", viewVO.getMajorName());
        }
        if (StringUtils.isNotEmpty(viewVO.getClassName())) {
            map.put("className-li", viewVO.getClassName());
        }
        if (StringUtils.isNotEmpty(viewVO.getCollege())) {
            map.put("college-li", viewVO.getCollege());
        }
        if (StringUtils.isNotEmpty(viewVO.getStuNo())) {
            map.put("stuNo-li", viewVO.getStuNo());
        }
        setCollegeIdMap(map);

        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
        List<SqlQueryItem> itemList = builder.builder(map);
        examViewRecordVOs = examViewService.getExaminationNotPassByFilter(itemList, null, page);
        mv.addObject("examrecord", examViewRecordVOs);
        mv.addObject("page", examViewRecordVOs.getNumber() + 1);
        mv.addObject("totalPage", examViewRecordVOs.getTotalPages());
        mv.addObject("totalCount", examViewRecordVOs.getTotalElements());
        //mv.addObject("schedulelist", scheduleService.getAllSchedule());
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
        setCollegeId(vo);
        ExcelSettings settings = new ExcelSettings(title, Constants.EXAM_RECORD_COLUMNS);
        try {
            response.reset();
            String fileName = title + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream");
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            creator.createExcel(settings, ((ExamNotPassExcelCreator) creator).new ExamNotPassDataProvider(), bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @RequestMapping(value = "/notParticipate")
//    public ModelAndView getNotParticipateStuList(StudentNotParticipateView vo,@PageableDefault Pageable page){
//        ModelAndView mv = new ModelAndView("exam.notParticipate-list");
//        Map<String, Object> map = new HashMap<>();
//        if (vo.getScheduleId() != null) {
//            map.put("scheduleId", vo.getScheduleId());
//            ExamSchedule schedule = scheduleService.getExamSchedule(vo.getScheduleId());
//            mv.addObject("scheduleName", schedule.getName());
//            mv.addObject("scheduleId", vo.getScheduleId());
//        }
//        if (StringUtils.isNotEmpty(vo.getStuMajor())) {
//            map.put("stuMajor-li", vo.getStuMajor());
//        }
//        if (StringUtils.isNotEmpty(vo.getStuClassName())) {
//            map.put("stuClassName-li", vo.getStuClassName());
//        }
//        if (StringUtils.isNotEmpty(vo.getStuNo())) {
//            map.put("stuNo-li", vo.getStuNo());
//        }
//        setCollegeIdMap(map);
//
//        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
//        List<SqlQueryItem> itemList = builder.builder(map);
//        Page<StudentNotParticipateView>  notpstus = examViewService.getNotParticipateStudents(itemList,null,page);
//        mv.addObject("notParticipate", notpstus);
//        mv.addObject("page", notpstus.getNumber() + 1);
//        mv.addObject("totalPage", notpstus.getTotalPages());
//        mv.addObject("totalCount", notpstus.getTotalElements());
//        //mv.addObject("schedulelist", scheduleService.getAllSchedule());
//        //搜索参数
//        if (vo == null) {
//            vo = new StudentNotParticipateView();
//        }
//        mv.addObject("query", vo);
//        return mv;
//    }
//
    @RequestMapping(value = "/notParticipateDownload")
    public void downloadNotParticipate(Long scheId,String className,HttpServletResponse response) {
        if (className!=null){
            try{
                className = new String(className.getBytes("ISO-8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
        ExcelCreator creator = new NotParticipateExcelCreator(scheduleService, scheId,className);
        String title = "";
        if (scheId != null && scheId!=-1) {
            ExamSchedule schedule = scheduleService.getExamSchedule(scheId);
            title = schedule.getName() + "考试未参考记录表";
        } else {
            title = "考试未参考记录表";
        }

        ExcelSettings settings = new ExcelSettings(title, Constants.NOT_PARTICIPATE_COLUMNS);
        try {
            response.reset();
            String fileName = title + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream");
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            creator.createExcel(settings, ((NotParticipateExcelCreator) creator).new NotParticipateDataProvider(), bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 考试统计，通过率
//     *
//     * @param scheduleid 考试安排编号
//     * @param className  班级
//     * @param page
//     * @return
//     */
//    @RequestMapping(value = "/passratelist")
//    public ModelAndView getExamRecordPassrateList(Long scheduleid, String className, @PageableDefault Pageable page) {
//        ModelAndView mv = new ModelAndView("exam.examquerypassrate-list");
//        Page<ExaminationViewPassRateVO> examViewRecordVOs = null;
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        if (scheduleid != null) {
//            map.put("scheduleid", scheduleid);
//            ExamSchedule schedule = scheduleService.getExamSchedule(scheduleid);
//            mv.addObject("scheduleName", schedule.getName());
//            mv.addObject("scheduleid", scheduleid);
//        }
//        if (!StringUtils.isEmpty(className)) {
//            map.put("className-li", className);
//        }
//        SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
//        List<SqlQueryItem> itemList = builder.builder(map);
//        examViewRecordVOs = examViewService.getExaminationPassRateByFilter(itemList, null, page);
//        mv.addObject("examrecord", examViewRecordVOs);
//        mv.addObject("page", examViewRecordVOs.getNumber() + 1);
//        mv.addObject("totalPage", examViewRecordVOs.getTotalPages());
//        mv.addObject("schedulelist", scheduleService.getAllSchedule());
//        //搜索参数
//        mv.addObject("scheduleid", scheduleid);
//        mv.addObject("className", className);
//        return mv;
//    }

    @RequestMapping(value = "/notParticipate", method = RequestMethod.GET)
    public ModelAndView notParticipate(Long scheId, String scheName, String className) {
        ModelAndView mv = new ModelAndView("exam.notParticipate-list");
        try{
            if (className != null) {
                className = new String(className.getBytes("ISO8859-1"), "UTF-8");
                mv.addObject("className", className);
            }
            if (scheName!=null){
                scheName = new String(scheName.getBytes("ISO8859-1"), "UTF-8");
                mv.addObject("scheName", scheName);
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (scheId != null) {
            mv.addObject("scheId", scheId);
        }else{
            mv.addObject("scheId", -1L);
        }
        return mv;
    }

    @RequestMapping(value = "/notParticipateData", method = RequestMethod.GET)
    @ResponseBody
    public List<StudentVO> notParticipateData(Long scheId, String className) {
        List<StudentVO> data = new ArrayList<>();
        if (scheId != null && scheId!=-1 && StringUtils.isNotEmpty(className)) {
            try{
                className = new String(className.getBytes("ISO8859-1"), "UTF-8");
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
            data = scheduleService.getScheduleParticipateStudent(scheId, "%"+className+"%");
        } else if (scheId != null && scheId!=-1 && StringUtils.isEmpty(className)) {
            data = scheduleService.getScheduleParticipateStudent(scheId);
        }
        return data;
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

    private void setCollegeId(ExaminationViewBaseClass vo) {
        ShiroDatabaseRealm.ShiroUser user = rbacService.getCurrentUser();
        Long collegeId = user.getCollegeId();
        if (collegeId != null && collegeId != -1) {
            vo.setCollegeId(collegeId);
        }
    }

    private void setCollegeIdMap(Map<String, Object> map) {
        ShiroDatabaseRealm.ShiroUser user = rbacService.getCurrentUser();
        if (user != null) {
            long collegeId = user.getCollegeId();
            if (collegeId != -1) {
                map.put("collegeId", collegeId);
            }
        }
    }
}
