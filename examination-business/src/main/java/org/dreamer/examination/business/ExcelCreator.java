package org.dreamer.examination.business;

import java.io.OutputStream;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface ExcelCreator<T> {

    public void createExcel(ExcelSettings settings,DataProvider<T> dataProvider,OutputStream os);
}
