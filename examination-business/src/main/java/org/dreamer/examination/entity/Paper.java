package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 学生的考试试卷
 *
 * @author lcheng
 * @version 1.0
 */
@Entity
@Table(name = "papers")
public class Paper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_PAPERS")
    @TableGenerator(name = "ID_PAPERS", table = "ids_gen", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VALUE", initialValue = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "temp_id")
    private ExamTemplate template;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String quesIdTxt;

    //参考人员标识
    @Column(length = 40)
    private String examStaffId;

    private Date createTime;

    //学生提交的答案
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String answers;

    @Column(scale = 1)
    private float finalScore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ExamTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ExamTemplate template) {
        this.template = template;
    }

    public String getQuesIdTxt() {
        return quesIdTxt;
    }

    public void setQuesIdTxt(String quesIdTxt) {
        this.quesIdTxt = quesIdTxt;
    }

    public String getExamStaffId() {
        return examStaffId;
    }

    public void setExamStaffId(String examStaffId) {
        this.examStaffId = examStaffId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
