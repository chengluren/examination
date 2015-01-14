package org.dreamer.examination.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.dreamer.examination.entity.ExaminationViewNotPassVO;
import org.dreamer.examination.entity.ExaminationViewVO;
import org.dreamer.examination.service.ExaminationViewService;
import org.dreamer.examination.sql.builder.SqlQueryModelBuilder;
import org.dreamer.examination.sql.model.SqlQueryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcheng on 2014/5/19.
 */
public class ExamNotPassExcelCreator extends AbstractExcelCreator<ExaminationViewNotPassVO> {

    private ExaminationViewService examService;
    private ExaminationViewNotPassVO queryParam;

    public ExamNotPassExcelCreator(ExaminationViewService examService, ExaminationViewNotPassVO queryParam) {
        this.examService = examService;
        this.queryParam = queryParam;
    }

    @Override
    public void createRow(Workbook workbook, Sheet sheet, ExaminationViewNotPassVO data, int rowNum) {
        CreationHelper createHelper = workbook.getCreationHelper();
        Row row = sheet.createRow(rowNum);
        Cell seqCell = row.createCell(0);
        seqCell.setCellValue(rowNum - 1);

        Cell nameCell = row.createCell(1);
        nameCell.setCellValue(data.getSchedulename());

        Cell examTimeCell = row.createCell(2);
        examTimeCell.setCellValue(data.getExamStartTime());
        CellStyle cs = workbook.createCellStyle();
        cs.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/m/d h:mm"));

        examTimeCell.setCellStyle(cs);

        Cell collegeCell = row.createCell(3);
        collegeCell.setCellValue(data.getCollege());

        Cell majorCell = row.createCell(4);
        majorCell.setCellValue(data.getMajorName());

        Cell classNameCell = row.createCell(5);
        classNameCell.setCellValue(data.getClassName());

        Cell stuNoCell = row.createCell(6);
        stuNoCell.setCellValue(data.getStuNo());

        Cell stuNameCell = row.createCell(7);
        stuNameCell.setCellValue(data.getStuName());

        Cell scoreCell = row.createCell(8);
        scoreCell.setCellValue(data.getFinalScore());
    }

    public class ExamNotPassDataProvider implements DataProvider<ExaminationViewNotPassVO> {
        @Override
        public Page<ExaminationViewNotPassVO> getNextPageData(Pageable page) {
            Map<String, Object> map = new HashMap<>();
            if (queryParam.getScheduleid() != null) {
                map.put("scheduleid", queryParam.getScheduleid());
            }
            if (StringUtils.isNotEmpty(queryParam.getMajorName())){
                map.put("majorName-li",queryParam.getMajorName());
            }
            if (StringUtils.isNotEmpty(queryParam.getClassName())) {
                map.put("className-li", queryParam.getClassName());
            }
            if (StringUtils.isNotEmpty(queryParam.getCollege())) {
                map.put("college-li", queryParam.getCollege());
            }
            if (StringUtils.isNotEmpty(queryParam.getStuNo())) {
                map.put("stuNo-li", queryParam.getStuNo());
            }
            if (queryParam.getCollegeId()!=null && queryParam.getCollegeId()!=-1){
                map.put("collegeId",queryParam.getCollegeId());
            }
            SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
            List<SqlQueryItem> itemList = builder.builder(map);
            return examService.getExaminationNotPassByFilter(itemList, null, page);
        }
    }
}
