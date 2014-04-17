package org.dreamer.examination.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class PaperQuestionVO implements Serializable{

    private List<Long> ids;
    private Long id;
    private float score;

    public PaperQuestionVO(List<Long> ids, float score) {
        this.ids = ids;
        this.score = score;
    }

    public PaperQuestionVO(Long id,float score){
        this.id = id;
        this.score = score;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
