package org.dreamer.examination.importer;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.dreamer.examination.entity.Question;
import org.dreamer.examination.entity.TrueOrFalseQuestion;

/**
 * Created by lcheng on 2014/4/16.
 */
public class TrueFalseQuesParser extends AbstractParser implements Parser {

    @Override
    public Question parse(Row row) {
        String stem = null;
        if (row!=null){
            Cell c = row.getCell(0);
            stem = (c!=null) ? (parseStem(row.getCell(0).getStringCellValue())) : null;
        }
        if (StringUtils.isNotEmpty(stem)){
            Cell acell = row.getCell(1);
            String answer = (acell!=null) ? acell.getStringCellValue() : "";
            answer = parseBooleanAnswer(answer);
            TrueOrFalseQuestion q = new TrueOrFalseQuestion();
            q.setStem(stem);
            q.setAnswer(answer);
            q.setDifficulty(Question.Difficulty.Easy);
            sysLog(stem, answer);
            return q;
        }else {
            return null;
        }
    }

    private void sysLog(String stem,String answer){
        System.out.println(stem);
        System.out.println("答案：" + answer);
    }
}
