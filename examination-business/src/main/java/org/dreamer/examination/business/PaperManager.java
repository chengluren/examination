package org.dreamer.examination.business;

import org.dreamer.examination.entity.*;
import org.dreamer.examination.service.ExamTemplateService;
import org.dreamer.examination.service.PaperService;
import org.dreamer.examination.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Component
public class PaperManager {

    @Autowired
    private PaperService paperService;
    @Autowired
    private ExamTemplateService templateService;
    @Autowired
    private QuestionService questionService;

    public Paper generateAndSavePaper(long tempId) {

        ExamTemplate template = templateService.getExamTemplate(tempId);
        if (null == template)
            return null;

        RandomStrategy randomStrategy = new DefaultQuestionRandomStrategy(questionService);

        Map<Types.QuestionType, List<String>> quesMap = new LinkedHashMap<>();
        List<MustChooseQuestionDef> mustChoose = template.getMustChooseDefs();
        List<TemplateQuestionDef> tempQuesDef = template.getQuestionDefs();

        randomStrategy.randomGenerate(tempQuesDef);
        Map<Types.QuestionType,StringBuilder> generatedStr = randomStrategy.generateStr();
        if (mustChoose != null && mustChoose.size() > 0) {
            for (MustChooseQuestionDef def : mustChoose) {
                Types.QuestionType type = def.getQuestionType();
                if (generatedStr.get(type) == null) {
                    generatedStr.put(def.getQuestionType(),new StringBuilder());
                }
                generatedStr.get(type).append(def.getQuestionId());
                generatedStr.get(type).append(",");
                generatedStr.get(type).append(def.getQuesScore());
                generatedStr.get(type).append("|");
            }
        }

        Paper p = new Paper();
        p.setTemplate(template);
        p.setCreateTime(new Date());
        p.setQuesIdTxt(concatGeneratedStr(generatedStr));
        paperService.addPaper(p);
        return p;
    }

   private String concatGeneratedStr(Map<Types.QuestionType,StringBuilder> generatedStr){
       StringBuilder sb = new StringBuilder();
       if (generatedStr.containsKey(Types.QuestionType.Choice)){
           sb.append(generatedStr.get(Types.QuestionType.Choice));
           sb.append("/");
           generatedStr.remove(Types.QuestionType.Choice);
       }else if (generatedStr.containsKey(Types.QuestionType.MultipleChoice)){
           sb.append(generatedStr.get(Types.QuestionType.MultipleChoice));
           sb.append("/");
           generatedStr.remove(Types.QuestionType.MultipleChoice);
       }else if(generatedStr.containsKey(Types.QuestionType.TrueFalse)){
           sb.append(generatedStr.get(Types.QuestionType.TrueFalse));
           sb.append("/");
           generatedStr.remove(Types.QuestionType.TrueFalse);
       }else if(generatedStr.containsKey(Types.QuestionType.Completion)){
           sb.append(generatedStr.get(Types.QuestionType.Completion));
           sb.append("/");
           generatedStr.remove(Types.QuestionType.Completion);
       }else if(generatedStr.containsKey(Types.QuestionType.ShortAnswer)){
           sb.append(generatedStr.get(Types.QuestionType.ShortAnswer));
           sb.append("/");
           generatedStr.remove(Types.QuestionType.ShortAnswer);
       }
       return sb.toString();
   }

}
