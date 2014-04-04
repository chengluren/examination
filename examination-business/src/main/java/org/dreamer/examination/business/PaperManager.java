package org.dreamer.examination.business;

import org.dreamer.examination.entity.*;
import org.dreamer.examination.service.ExamTemplateService;
import org.dreamer.examination.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Paper generatePaper(long tempId){

        ExamTemplate template = templateService.getExamTemplate(tempId);
        if(null == template)
            return null;
        Map<Types.QuestionType,List<String>> quesMap = new LinkedHashMap<>();
        List<MustChooseQuestionDef> mustChoose =  template.getMustChooseDefs();
        List<TemplateQuestionDef> tempQuesDef = template.getQuestionDefs();

        if (mustChoose!=null&&mustChoose.size()>0){
          //TODO
        }
        return null;
    }


}
