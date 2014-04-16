package org.dreamer.examination.utils;

import org.dreamer.examination.entity.Types;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class QuestionTypeUtils {

    public static List<String> getOrderedType(Set<Types.QuestionType> types,boolean multiChoiceMixed){
        if (types!=null){
            List<String> result = new ArrayList<>();
            if (types.contains(Types.QuestionType.Choice)){
                result.add(Types.QuestionType.Choice.getShortName());
            }

            if (types.contains(Types.QuestionType.MultipleChoice) && !multiChoiceMixed){
                 result.add(Types.QuestionType.MultipleChoice.getShortName());
            }else if (types.contains(Types.QuestionType.MultipleChoice) && multiChoiceMixed &&
                    !types.contains(Types.QuestionType.Choice)){
                 result.add(Types.QuestionType.Choice.getShortName());
            }

            if (types.contains(Types.QuestionType.TrueFalse)){
                result.add(Types.QuestionType.TrueFalse.getShortName());
            }

            if (types.contains(Types.QuestionType.Completion)){
                result.add(Types.QuestionType.Completion.getShortName());
            }

            if (types.contains(Types.QuestionType.ShortAnswer)){
                result.add(Types.QuestionType.ShortAnswer.getShortName());
            }
            return result;
        }
        return null;
    }
}
