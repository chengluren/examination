package org.dreamer.examination.importer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.dreamer.examination.entity.Question;
import org.dreamer.examination.search.QuestionIndexer;
import org.dreamer.examination.service.QuestionService;
import org.dreamer.examination.utils.SysUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class DefaultExcelImporter implements Importer {

    private QuestionService questionService;

    public DefaultExcelImporter(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void doImport(File file,long storeId) {
        try (InputStream is = new FileInputStream(file)) {
            Workbook wb = WorkbookFactory.create(is);
            doImportInternal(wb.getSheetAt(0),new ChoiceQuesParser(),storeId);
            doImportInternal(wb.getSheetAt(1),new MultiChoiceQuesParser(),storeId);
            doImportInternal(wb.getSheetAt(2),new TrueFalseQuesParser(),storeId);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    private void doImportInternal(Sheet sheet,Parser p,long storeId){
        int maxRow = sheet.getLastRowNum();
        List<Question> list = new ArrayList<>();
        for (int i=1;i<=maxRow;i++){
            Row row = sheet.getRow(i);
            Question q = p.parse(row);
            if (q!=null){
                q.setStoreId(storeId);
                list.add(q);
            }
        }
        if(list.size()>0){
            questionService.addQuestion(list);
            String type = SysUtils.getConfigValue("question.list.type","db");
            boolean index = type.equals("index") ? true: false;
            if (index){
                QuestionIndexer indexer = SysUtils.getBean(QuestionIndexer.class);
                indexer.indexQuestions(list);
            }
        }
    }
}
