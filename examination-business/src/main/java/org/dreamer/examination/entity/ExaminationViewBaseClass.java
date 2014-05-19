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
    @Column(name = "majorName")
    private String majorName;
    @Column(name = "stuName")
    private String stuName;
    @Column(name = "college")
    private String college;

    @Column(name = "collegeId")
    private Long collegeId;

    @Column(name = "className")
    private String className;

    @Column(name = "schedulename")
    private String schedulename;

    @Column(name = "scheduleid")
    private Long scheduleid;

    @Column(name = "examStaffId")
    private String examStaffId;
    @Column(name = "stuNo")
    private String stuNo;

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

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }
}