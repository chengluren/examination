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

    @Id()
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_STORES")
    @TableGenerator(name = "ID_STORES", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private long id;

    @Column(length = 50)
    private long name;

    @Column(length=200)
    private String comment;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
