package org.dreamer.examination.business;

import org.dreamer.examination.vo.PaperQuestionVO;
import org.dreamer.examination.entity.TemplateQuestionDef;
import org.dreamer.examination.entity.Types;

import java.util.List;
import java.util.Map;

/**
 *随机抽取试题的策略
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface TemplateRandomStrategy {

    //Set<Long> randomGenerate(TemplateQuestionDef def);

//    public Map<Types.QuestionType,Set<Long>> randomGenerate(List<TemplateQuestionDef> defs);
//
//    public Map<Types.QuestionType,StringBuilder> generateStr();

    public Map<Types.QuestionType,List<PaperQuestionVO>> randomGenerate(List<TemplateQuestionDef> defs);
}
