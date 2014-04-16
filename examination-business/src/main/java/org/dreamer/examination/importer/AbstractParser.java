package org.dreamer.examination.importer;

import com.google.common.base.Joiner;

import java.util.Arrays;
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

    protected String[] parseOption(String content){
        String[] r = new String[2];
        r[0] = String.valueOf(content.charAt(0));
        r[1] = content.substring(2).trim();
        return r;
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
