package org.dreamer.examination.entity;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class FacadeQuestionVO {

    private Question q;
    private float score;

    public FacadeQuestionVO(Question q,float score){
        this.q = q;
        this.score = score;
    }

    public Question getQ() {
        return q;
    }

    public void setQ(Question q) {
        this.q = q;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
