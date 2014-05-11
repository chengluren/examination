package org.dreamer.examination.vo;

import org.dreamer.examination.entity.QuestionOption;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class QuestionVO {

    private Long id;
    private String stem;
    private String answer;
    private String imgPath;
    private boolean mustChoose;
    private QuestionOption[] options;
    private Long storeId;
    private String quesType;

    public QuestionVO(){}

    public QuestionVO(Long id,String stem,String answer){
        this.id = id;
        this.stem = stem;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public boolean isMustChoose() {
        return mustChoose;
    }

    public void setMustChoose(boolean mustChoose) {
        this.mustChoose = mustChoose;
    }

    public QuestionOption[] getOptions() {
        return options;
    }

    public void setOptions(QuestionOption[] options) {
        this.options = options;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getQuesType() {
        return quesType;
    }

    public void setQuesType(String quesType) {
        this.quesType = quesType;
    }
}
