package org.dreamer.examination.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 角色表
 */
@MappedSuperclass
public abstract class ExaminationViewBaseClass {
    @Id
    private long id;

    @Column(name = "major")
    private String major;

    @Column(name = "schedulename")
    private String schedulename;

    @Column(name = "scheduleid")
    private Long scheduleid;

    @Column(name = "examStaffId")
    private String examStaffId;

    @Column(name = "passScore")
    private Double passScore;

    @Column(name = "finalScore")
    private Double finalScore;

    @Column(name = "paperid")
    private String paperid;

    @Column(name = "examStartTime")
    private Date examStartTime;

    public Date getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(Date examStartTime) {
        this.examStartTime = examStartTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchedulename() {
        return schedulename;
    }

    public void setSchedulename(String schedulename) {
        this.schedulename = schedulename;
    }

    public String getExamStaffId() {
        return examStaffId;
    }

    public void setExamStaffId(String examStaffId) {
        this.examStaffId = examStaffId;
    }

    public Double getPassScore() {
        return passScore;
    }

    public void setPassScore(Double passScore) {
        this.passScore = passScore;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }


    public Long getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Long scheduleid) {
        this.scheduleid = scheduleid;
    }



}