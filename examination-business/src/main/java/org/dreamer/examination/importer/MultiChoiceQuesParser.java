package org.dreamer.examination.importer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.dreamer.examination.entity.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcheng on 2014/4/16.
 */
public class MultiChoiceQuesParser extends AbstractParser implements Parser{

    @Override
    public Question parse(Row row) {
        short max = row.getLastCellNum();
        String stem = parseStem(row.getCell(0).getStringCellValue());
        List<String> options = new ArrayList<>();
        for (int i=1;i<(max-1);i++){
            Cell c = row.getCell(i);
            if(c!=null){
                String op = row.getCell(i).getStringCellValue();
                if (op!=null&&!op.equals("")){
                    options.add(op.substring(2).trim());
                }
            }

        }
        String answer = row.getCell(max-1).getStringCellValue();
        answer = parseMultiChoiceAnswer(answer);
        System.out.println(stem);
        char c = 64;
        for (int j=0;j<options.size();j++){
            c++;
            System.out.println(c+"."+options.get(j));
        }
        System.out.println("答案："+answer);
        return null;
    }

    public static void main(String[] args){
        Parser p = new MultiChoiceQuesParser();
        try (InputStream is = new FileInputStream("")) {
            Workbook wb = WorkbookFactory.create(is);
            Sheet s = wb.getSheetAt(1);
            int endRow =s.getLastRowNum();
            for(int i=1;i<endRow;i++){
                System.out.println("==========="+i+"===============");
                p.parse(s.getRow(i));
                System.out.println("===============================");
            }
        }catch (IOException |InvalidFormatException e){
            e.printStackTrace();
        }

    }
}
