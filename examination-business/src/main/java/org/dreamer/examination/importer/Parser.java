package org.dreamer.examination.importer;


import org.dreamer.examination.entity.Question;
import org.apache.poi.ss.usermodel.*;
/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface Parser {

    public Question parse(Row row);
}
