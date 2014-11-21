package org.dreamer.examination.business;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dreamer.examination.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public abstract class AbstractExcelCreator<T> implements ExcelCreator<T> {

    public void createTitle(Workbook workbook, Sheet sheet, String title, int cells) {
        Row row = sheet.createRow(0);
        row.setHeightInPoints((short) 25);
        Cell cell = row.createCell(0);
        cell.setCellValue(title);
        CellStyle cs = workbook.createCellStyle();
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        Font f = workbook.createFont();
        f.setFontHeightInPoints((short) 18);
        cs.setFont(f);
        cell.setCellStyle(cs);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cells));
    }

    public void createColumn(Workbook workbook, Sheet sheet, String[] columns) {
        if (columns != null && columns.length > 0) {
            CellStyle cs = workbook.createCellStyle();
            cs.setAlignment(CellStyle.ALIGN_CENTER);
            int len = columns.length;
            Row row = sheet.createRow(1);
            for (int i = 0; i < len; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(cs);
            }
        }
    }

    public abstract void createRow(Workbook workbook, Sheet sheet, T data, int rowNum);

    @Override
    public void createExcel(ExcelSettings settings, DataProvider<T> dataProvider, OutputStream os) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        createTitle(workbook, sheet, settings.getTitle(), settings.getColumns().length-1);
        createColumn(workbook, sheet, settings.getColumns());

        Pageable page = new PageRequest(0, Constants.DEFAULT_PAGE_SIZE);
        Page<T> data = dataProvider.getNextPageData(page);
        int currPage = 0;
        int totalPage = data.getTotalPages();
        int rowNum = 1;
        while (currPage < totalPage) {
            List<T> rowData = data.getContent();
            for (T t : rowData) {
                createRow(workbook, sheet, t, rowNum + 1);
                rowNum++;
            }
            currPage++;
            data = dataProvider.getNextPageData(new PageRequest(currPage, Constants.DEFAULT_PAGE_SIZE));
        }
        try {
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
