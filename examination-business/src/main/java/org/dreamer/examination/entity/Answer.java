package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lcheng on 2014/4/13.
 */
@Entity
@Table(name = "answers")
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_ANSWERS")
    @TableGenerator(name = "ID_ANSWERS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private long id;
    private long quesId;
    private long examId;
    private String answer;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String txtAnswer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuesId() {
        return quesId;
    }

    public void setQuesId(long quesId) {
        this.quesId = quesId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTxtAnswer() {
        return txtAnswer;
    }

    public void setTxtAnswer(String txtAnswer) {
        this.txtAnswer = txtAnswer;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }
}
