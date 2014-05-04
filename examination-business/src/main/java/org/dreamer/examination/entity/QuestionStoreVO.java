package org.dreamer.examination.entity;

import java.io.Serializable;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class QuestionStoreVO implements Serializable {

    private long id;
    private String name;
    private long quesCount;
    private String comment;
    private boolean generic;

    public QuestionStoreVO(){}

    public QuestionStoreVO(long id,String name,long quesCount,
                           boolean generic,String comment){
        this.id = id;
        this.name = name;
        this.quesCount = quesCount;
        this.generic = generic;
        this.comment = comment;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuesCount() {
        return quesCount;
    }

    public void setQuesCount(long quesCount) {
        this.quesCount = quesCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }
}
