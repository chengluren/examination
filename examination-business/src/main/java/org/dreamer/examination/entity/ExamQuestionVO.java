package org.dreamer.examination.entity;

import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class ExamQuestionVO {

    private long quesId;
    private String stem;
    private String imgPath;
    private float score;
    private List<QuestionOption> options;

    public ExamQuestionVO(){}
    public ExamQuestionVO(long quesId,String stem,String imgPath,float score){
        this.quesId = quesId;
        this.stem = stem;
        this.imgPath = imgPath;
        this.score = score;
    }


    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }

    public long getQuesId() {
        return quesId;
    }

    public void setQuesId(long quesId) {
        this.quesId = quesId;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOption> options) {
        this.options = options;
    }
}
