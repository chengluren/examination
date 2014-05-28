package org.dreamer.examination.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.dreamer.examination.entity.ExaminationViewPassVO;
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
public class ExamPassExcelCreator extends AbstractExcelCreator<ExaminationViewPassVO> {

    private ExaminationViewService examService;

    private ExaminationViewPassVO queryParam;

    public ExamPassExcelCreator(ExaminationViewService examService, ExaminationViewPassVO queryParam) {
        this.examService = examService;
        this.queryParam = queryParam;
    }

    @Override
    public void createRow(Workbook workbook, Sheet sheet, ExaminationViewPassVO data, int rowNum) {
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

        Cell classNameCell = row.createCell(3);
        classNameCell.setCellValue(data.getClassName());

        Cell stuNoCell = row.createCell(4);
        stuNoCell.setCellValue(data.getStuNo());

        Cell stuNameCell = row.createCell(5);
        stuNameCell.setCellValue(data.getStuName());

        Cell scoreCell = row.createCell(6);
        scoreCell.setCellValue(data.getFinalScore());
    }

    public class ExamPassDataProvider implements DataProvider<ExaminationViewPassVO> {
        @Override
        public Page<ExaminationViewPassVO> getNextPageData(Pageable page) {
            Map<String, Object> map = new HashMap<>();
            if (queryParam.getScheduleid() != null) {
                map.put("scheduleid", queryParam.getScheduleid());
            }
            if (StringUtils.isNotEmpty(queryParam.getMajorName())) {
                map.put("majorName-li", queryParam.getMajorName());
            }
            if (StringUtils.isNotEmpty(queryParam.getClassName())) {
                map.put("className", queryParam.getClassName());
            }
            if (StringUtils.isNotEmpty(queryParam.getStuNo())) {
                map.put("stuNo", queryParam.getStuNo());
            }
            SqlQueryModelBuilder builder = new SqlQueryModelBuilder();
            List<SqlQueryItem> itemList = builder.builder(map);
            return examService.getExaminationPassByFilter(itemList, null, page);
        }
    }
}