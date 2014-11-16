package org.dreamer.examination.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.dreamer.examination.entity.StudentNotParticipateView;
import org.dreamer.examination.service.ExamScheduleService;
import org.dreamer.examination.service.ExaminationViewService;
import org.dreamer.examination.sql.builder.SqlQueryModelBuilder;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.dreamer.examination.vo.StudentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class NotParticipateExcelCreator extends AbstractExcelCreator<StudentVO> {

    private ExamScheduleService scheduleService;
    private Long scheduleId;
    private String className;

    public NotParticipateExcelCreator(ExamScheduleService scheduleService,Long scheduleId,String className){
        this.scheduleService = scheduleService;
        this.scheduleId = scheduleId;
        this.className = className;
    }

    @Override
    public void createRow(Workbook workbook, Sheet sheet, StudentVO data, int rowNum) {
        CreationHelper createHelper = workbook.getCreationHelper();
        Row row = sheet.createRow(rowNum);
        Cell seqCell = row.createCell(0);
        seqCell.setCellValue(rowNum - 1);

        Cell collegeCell = row.createCell(1);
        collegeCell.setCellValue(data.getCollege());

        Cell majorCell = row.createCell(2);
        majorCell.setCellValue(data.getMajor());

        Cell stuClassCell = row.createCell(3);
        stuClassCell.setCellValue(data.getClassName());

        Cell stuNoCell = row.createCell(4);
        stuNoCell.setCellValue(data.getName());

        Cell stuNameCell = row.createCell(5);
        stuNameCell.setCellValue(data.getStuId());
    }

    public class NotParticipateDataProvider implements DataProvider<StudentVO>{
        @Override
        public Page<StudentVO> getNextPageData(Pageable page) {
            List<StudentVO> data = new ArrayList<>();
            if (scheduleId != null && scheduleId!=-1 && StringUtils.isNotEmpty(className)) {
                data = scheduleService.getScheduleParticipateStudent(scheduleId, "%"+className+"%");
            } else if (scheduleId != null && scheduleId!=-1 && StringUtils.isEmpty(className)) {
                data = scheduleService.getScheduleParticipateStudent(scheduleId);
            }
            Page<StudentVO> result = new PageImpl<>(data,new PageRequest(0,data.size()),data.size());
            return result;
        }
    }

//    public class NotParticipateDataProvider implements DataProvider<StudentNotParticipateView> {
//        @Override
//        public Page<StudentNotParticipateView> getNextPageData(Pageable page) {
//            Map<String, Object> map = new HashMap<>();
//            if (queryParam.getScheduleId() != null) {
//                map.put("scheduleId", queryParam.getScheduleId());
//            }
//            if (StringUtils.isNotEmpty(queryParam.getStuMajor())) {
//                map.put("stuMajor-li", queryParam.getStuMajor());
//            }
//            if (StringUtils.isNotEmpty(queryParam.getStuClassName())) {
//                map.put("stuClassName-li", queryParam.getStuClassName());
//            }
//            if (StringUtils.isNotEmpty(queryParam.getStuNo())) {
//                map.put("stuNo-li", queryParam.getStuNo());
//            }
//            if (queryParam.getCollegeId()!=null && queryParam.getCollegeId()!=-1){
//                map.put("collegeId",queryParam.getCollegeId());
//            }
////            SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
////            List<SqlQueryItem> itemList = builder.builder(map);
////            return examService.getNotParticipateStudents(itemList, null, page);
//            return null;
//        }
//    }
}
