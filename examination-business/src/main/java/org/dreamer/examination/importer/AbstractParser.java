package org.dreamer.examination.importer;

import com.google.common.base.Joiner;
import org.dreamer.examination.entity.QuestionOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public abstract class AbstractParser implements Parser {

    protected String parseBooleanAnswer(String answer) {
        answer = answer.trim();
        String r = "0";
        if (answer.equals("对") || answer.equals("正确") || answer.equals("√")) {
            r = "1";
        }
        return r;
    }

    protected String parseMultiChoiceAnswer(String answer) {
        answer = answer.trim();
        String r = "";
        if (!answer.contains(",")) {
            char[] ca = answer.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ca.length; i++) {
                sb.append(ca[i] + ",");
            }
            if (sb.length()>0 && sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            r = sb.toString();
        } else {
            r = answer;
        }
        return r;
    }

    protected List<QuestionOption> parseOption(List<String> ops){
        if (ops!=null && ops.size()>0){
            List<QuestionOption> result = new ArrayList<>();
            char c = 64;
            for (int j = 0; j < ops.size(); j++) {
                c++;
                QuestionOption qo = new QuestionOption(ops.get(j),String.valueOf(c));
                result.add(qo);
            }
            return result;
        }
        return null;
    }

    protected String parseStem(String content) {
        int dotIndex = content.indexOf(".");
        int rsbIndex = content.indexOf("]");
        if (dotIndex>0 && rsbIndex>dotIndex){
            return content.substring(rsbIndex+1).trim();
        }else if(dotIndex>0 && dotIndex<5){
            return content.substring(dotIndex+1).trim();
        }else if (rsbIndex>0){
            return content.substring(rsbIndex+1).trim();
        }else {
            return content;
        }
    }
}
