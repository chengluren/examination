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

//    @Override
//    public Question parse(Row row){
//        if (row!=null){
//            Cell stemCell = row.getCell(0);
//            String stem = (stemCell !=null) ? parseStem(stemCell.getStringCellValue()) : null;
//
//            Cell difficultyCell = row.getCell(1);
//            Question.Difficulty difficulty = (difficultyCell!=null) ?
//                    parseDifficulty(difficultyCell.getStringCellValue()) : null;
//
//            Cell mcCell = row.getCell(2);
//            boolean mustChoose = (mcCell!=null) ? parseMustChoose(mcCell.getStringCellValue()) : false;
//
//            Cell anaCell = row.getCell(3);
//
//            Cell answerCell = row.getCell(4);
//            String answerStr = answerCell.getStringCellValue().trim();
//            String answer = (answerCell!=null) ? parseBooleanAnswer(answerStr) : null;
//
//            TrueOrFalseQuestion q = new TrueOrFalseQuestion();
//            q.setStem(stem);
//            q.setAnswer(answer);
//            q.setMustChoose(mustChoose);
//            q.setDifficulty(difficulty);
//
//            sysLog(stem, answer);
//            return q;
//        }
//        return null;
//    }

    @Override
    public Question parse(Row row) {
        String stem = null;
        if (row!=null){
            Cell c = row.getCell(0);
            stem = (c!=null) ? (parseStem(row.getCell(0).getStringCellValue())) : null;
        }
        if (StringUtils.isNotEmpty(stem)){
            Cell acell = row.getCell(1);
            String answer = (acell!=null) ? acell.getStringCellValue().trim() : "";
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
