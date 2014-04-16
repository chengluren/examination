package org.dreamer.examination.entity;

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
}
