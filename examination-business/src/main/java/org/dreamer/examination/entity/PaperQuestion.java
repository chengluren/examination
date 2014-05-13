package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lcheng on 2014/4/17.
 */
@Entity
@Table(name = "paper_questions")
public class PaperQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_PAPER_QUES")
    @TableGenerator(name = "ID_PAPER_QUES", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private Long id;
    //private Long examId;
    @Enumerated(EnumType.STRING)
    private Types.QuestionType quesType;
    private Long quesId;
    private float score;

    @ManyToOne
    @JoinColumn(name = "paper_id")
    private Paper paper;

    public PaperQuestion() {
    }

    public PaperQuestion(Types.QuestionType quesType,
                         Long quesId, float score) {
//        this.examId = examId;
        this.quesType = quesType;
        this.quesId = quesId;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getExamId() {
//        return examId;
//    }
//
//    public void setExamId(Long examId) {
//        this.examId = examId;
//    }

    public Types.QuestionType getQuesType() {
        return quesType;
    }

    public void setQuesType(Types.QuestionType quesType) {
        this.quesType = quesType;
    }

    public Long getQuesId() {
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
}
