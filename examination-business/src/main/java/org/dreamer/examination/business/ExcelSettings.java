package org.dreamer.examination.business;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class ExcelSettings {

    private String title;

    private String[] columns;

    public ExcelSettings(){}
    public ExcelSettings(String title,String[] columns){
        this.title = title;
        this.columns = columns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }
}
