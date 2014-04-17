package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 题库描述
 * Created by lcheng on 14-3-30.
 */
@Entity
@Table(name = "ques_stores")
public class QuestionStore implements Serializable {

    public QuestionStore(){}

    public QuestionStore(String name,String comment){
        this.name = name;
        this.comment = comment;
    }

    @Id()
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_STORES")
    @TableGenerator(name = "ID_STORES", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private Long id;

    @Column(length = 50)
    private String name;

    private Long parentId;

    @Column(length=200)
    private String comment;
    //序号
    private int sno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }
}
