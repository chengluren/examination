package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 记录参考人员的一次考试
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Entity
@Table(name="examinations")
public class Examination implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_EXAMS")
    @TableGenerator(name = "ID_EXAMS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private long id;

    @Column(length = 40)
    private String examStaffId;

    @OneToOne
    @JoinColumn(name = "paper_id")
    private Paper paper;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String answers;

    @Column(scale = 1)
    private float finalScore;

    private Date examStartTime;

    private Date answerCommitTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExamStaffId() {
        return examStaffId;
    }

    public void setExamStaffId(String examStaffId) {
        this.examStaffId = examStaffId;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
    }

    public Date getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(Date examStartTime) {
        this.examStartTime = examStartTime;
    }

    public Date getAnswerCommitTime() {
        return answerCommitTime;
    }

    public void setAnswerCommitTime(Date answerCommitTime) {
        this.answerCommitTime = answerCommitTime;
    }
}
