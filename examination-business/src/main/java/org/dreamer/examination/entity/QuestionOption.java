package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lcheng on 14-3-9.
 */
@Entity
@Table(name = "options")
public class QuestionOption implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_QUE_OPTS")
    @TableGenerator(name = "ID_QUE_OPTS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private Long id;

    @Column(nullable = false)
    private String content;

    //序号：可以是A,B,C,D...或者是1,2,3,4..
    @Column(nullable = false, length = 2)
    private String orderNo;

//    @ManyToOne
//    @JoinColumn(name = "ques_id")
//    private ChoiceQuestion question;

    public QuestionOption() {
    }

    public QuestionOption(String content, String orderNo) {
        this.content = content;
        this.orderNo = orderNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

//    public ChoiceQuestion getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(ChoiceQuestion question) {
//        this.question = question;
//    }
}
