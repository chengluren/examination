package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 必选题规则
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Entity
@Table(name = "must_choose_defs")
public class MustChooseQuestionDef implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_MUST_QUES_DEFS")
    @TableGenerator(name = "ID_MUST_QUES_DEFS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private long id;

    @Enumerated(EnumType.STRING)
    private Types.QuestionType questionType;

    private long questionId;

    @Column(scale = 1)
    private float quesScore;

    @ManyToOne
    @JoinColumn(name = "TEMP_ID")
    private ExamTemplate template;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Types.QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Types.QuestionType questionType) {
        this.questionType = questionType;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public float getQuesScore() {
        return quesScore;
    }

    public void setQuesScore(float quesScore) {
        this.quesScore = quesScore;
    }

    public ExamTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ExamTemplate template) {
        this.template = template;
    }
}
