package org.dreamer.examination.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class ExamRecordVO implements Serializable {
    private String staffId;
    private String examName;
    private float finalScore;
    private Date examDate;

    public ExamRecordVO() {
    }

    public ExamRecordVO(String staffId, String examName,
                        float finalScore, Date examDate) {
        this.staffId = staffId;
        this.examName = examName;
        this.finalScore = finalScore;
        this.examDate = examDate;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
}
