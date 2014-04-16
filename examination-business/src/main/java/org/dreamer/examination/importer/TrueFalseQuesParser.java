package org.dreamer.examination.importer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.dreamer.examination.entity.Question;
import org.dreamer.examination.entity.TrueOrFalseQuestion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lcheng on 2014/4/16.
 */
public class TrueFalseQuesParser extends AbstractParser implements Parser {

    @Override
    public Question parse(Row row) {
        String stem = parseStem(row.getCell(0).getStringCellValue());
        String answer = row.getCell(1).getStringCellValue();
        answer = parseBooleanAnswer(answer);
        TrueOrFalseQuestion q = new TrueOrFalseQuestion();
        q.setStem(stem);
        q.setAnswer(answer);
        System.out.println(stem);
        System.out.println("答案：" + answer);
        return q;
    }

    public static void main(String[] args) {
        Parser p = new TrueFalseQuesParser();
        try (InputStream is = new FileInputStream("")) {
            Workbook wb = WorkbookFactory.create(is);
            Sheet s = wb.getSheetAt(2);
            int endRow = s.getLastRowNum();
            for (int i = 1; i < endRow; i++) {
                System.out.println("===========" + i + "===============");
                p.parse(s.getRow(i));
                System.out.println("===============================");
            }
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

    }
}
