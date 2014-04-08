package org.dreamer.examination.business;

import com.google.common.base.Joiner;
import org.dreamer.examination.entity.TemplateQuestionDef;
import org.dreamer.examination.entity.Types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public abstract class AbstractRandomStrategy implements RandomStrategy{

    private Map<Types.QuestionType,Set<Long>> generated;

    private Map<Types.QuestionType,StringBuilder> generatedStr;

    public AbstractRandomStrategy(){
        generated = new HashMap<>();
        generatedStr = new HashMap<>();
    }

    public abstract Set<Long> randomGenerate(TemplateQuestionDef def);

    @Override
    public Map<Types.QuestionType,Set<Long>> randomGenerate(List<TemplateQuestionDef> defs) {
        if (defs == null||defs.size()==0)
            return generated;
        for (TemplateQuestionDef def : defs){
            Set<Long> typeGen = randomGenerate(def);
            Types.QuestionType type = def.getQuestionType();

            if (generated.containsKey(type)){
                generated.get(type).addAll(typeGen);
            }else {
                generated.put(type,typeGen);
            }
            if (!generatedStr.containsKey(type)){
                generatedStr.put(type,new StringBuilder());
            }
            Joiner.on("-").appendTo(generatedStr.get(type),typeGen);
            generatedStr.get(type).append(",");
            generatedStr.get(type).append(def.getScorePer());
            generatedStr.get(type).append("|");
        }
        return generated;
    }

    public Map<Types.QuestionType,Set<Long>> getGenerated(){
        return generated;
    }

    @Override
    public Map<Types.QuestionType, StringBuilder> generateStr() {
        return generatedStr;
    }
}
