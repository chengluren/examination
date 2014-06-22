package org.dreamer.examination.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.dreamer.examination.entity.StudentNotParticipateView;
import org.dreamer.examination.service.ExaminationViewService;
import org.dreamer.examination.sql.builder.SqlQueryModelBuilder;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class NotParticipateExcelCreator extends AbstractExcelCreator<StudentNotParticipateView> {

    private ExaminationViewService examService;
    private StudentNotParticipateView queryParam;

    public NotParticipateExcelCreator(ExaminationViewService examService, StudentNotParticipateView queryParam) {
        this.examService = examService;
        this.queryParam = queryParam;
    }

    @Override
    public void createRow(Workbook workbook, Sheet sheet, StudentNotParticipateView data, int rowNum) {
        CreationHelper createHelper = workbook.getCreationHelper();
        Row row = sheet.createRow(rowNum);
        Cell seqCell = row.createCell(0);
        seqCell.setCellValue(rowNum - 1);

        Cell nameCell = row.createCell(1);
        nameCell.setCellValue(data.getScheduleName());

        Cell collegeCell = row.createCell(2);
        collegeCell.setCellValue(data.getCollegeName());

        Cell majorCell = row.createCell(3);
        majorCell.setCellValue(data.getStuMajor());

        Cell stuClassCell = row.createCell(4);
        stuClassCell.setCellValue(data.getStuClassName());

        Cell stuNoCell = row.createCell(5);
        stuNoCell.setCellValue(data.getStuNo());

        Cell stuNameCell = row.createCell(6);
        stuNameCell.setCellValue(data.getStuName());
    }

    public class NotParticipateDataProvider implements DataProvider<StudentNotParticipateView> {
        @Override
        public Page<StudentNotParticipateView> getNextPageData(Pageable page) {
            Map<String, Object> map = new HashMap<>();
            if (queryParam.getScheduleId() != null) {
                map.put("scheduleId", queryParam.getScheduleId());
            }
            if (StringUtils.isNotEmpty(queryParam.getStuMajor())) {
                map.put("stuMajor-li", queryParam.getStuMajor());
            }
            if (StringUtils.isNotEmpty(queryParam.getStuClassName())) {
                map.put("stuClassName-li", queryParam.getStuClassName());
            }
            if (StringUtils.isNotEmpty(queryParam.getStuNo())) {
                map.put("stuNo-li", queryParam.getStuNo());
            }
            if (queryParam.getCollegeId()!=null && queryParam.getCollegeId()!=-1){
                map.put("collegeId",queryParam.getCollegeId());
            }
            SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
            List<SqlQueryItem> itemList = builder.builder(map);
            return examService.getNotParticipateStudents(itemList, null, page);
        }
    }
}
