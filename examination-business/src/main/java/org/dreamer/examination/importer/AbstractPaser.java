package org.dreamer.examination.importer;

import java.util.regex.Pattern;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public abstract class AbstractPaser implements Parser {

    protected String parseBooleanAnswer(String answer) {
        answer = answer.trim();
        String r = "0";
        if (answer.equals("对") || answer.equals("正确") || answer.equals("√")) {
            r = "1";
        }
        return r;
    }

    protected String paserMultiChoiceAnswer(String answer) {
        answer = answer.trim();
        String r = "";
        //TODO
        return r;
    }
}
