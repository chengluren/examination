package org.dreamer.examination.importer;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.dreamer.examination.entity.ChoiceQuestion;
import org.dreamer.examination.entity.Question;
import org.dreamer.examination.entity.QuestionOption;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class ChoiceQuesParser extends AbstractParser implements Parser {

    @Override
    public Question parse(Row row) {
        String stem = null;
        short max = -1;
        if (row!=null){
            max = row.getLastCellNum();
            Cell stemCell = row.getCell(0);
            if (stemCell!=null){
                stem = parseStem(stemCell.getStringCellValue());
            }
        }
        if (StringUtils.isNotEmpty(stem)){
            List<String> options = new ArrayList<>();
            for (int i = 1; i < (max - 1); i++) {
                Cell c = row.getCell(i);
                if (c != null) {
                    String op = row.getCell(i).getStringCellValue();
                    if (op != null && op.length()>2) {
                        options.add(op.substring(2).trim());
                    }
                }

            }
            String answer = row.getCell(max - 1).getStringCellValue();
            sysLogQuestions(stem,options,answer);

            ChoiceQuestion q = new ChoiceQuestion();
            q.setStem(stem);
            q.setAnswer(answer);
            q.setDifficulty(Question.Difficulty.Easy);

            List<QuestionOption> ops = parseOption(options);
            q.setQuestionOptions(ops);
            return q;
        } else{
            return null;
        }
    }

    private void sysLogQuestions(String stem,List<String> options,String answer){
        System.out.println(stem);
        char c = 64;
        for (int j = 0; j < options.size(); j++) {
            c++;
            System.out.println(c + "." + options.get(j));
        }
        System.out.println("答案：" + answer);
    }
}
