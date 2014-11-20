package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 记录参考人员的一次考试
 * @author lcheng
 * @version 1.0
 *
 */
@Entity
@Table(name="examinations")
public class Examination implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_EXAMS")
    @TableGenerator(name = "ID_EXAMS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private long id;

    @Column(length = 10)
    private String examStaffId;

    @OneToOne(cascade ={CascadeType.ALL})
    @JoinColumn(name = "paper_id")
    private Paper paper;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private ExamSchedule schedule;

    @Column(scale = 1)
    private float finalScore;

    private Date examStartTime;

    private Date commitTime;

    @Column(name = "scheduleStartTime")
    private Date scheduleStartTime;
    @Column(name = "scheduleEndTime")
    private Date scheduleEndTime;

    @Column(name = "major")
    private String major;
    @Column(name = "majorName")
    private String majorName;
    @Column(name = "stuNo")
    private String stuNo;
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

    @Column(name = "passScore")
    private Float passScore;

    @Column(name="promise")
    private Integer promise;

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

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public ExamSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(ExamSchedule schedule) {
        this.schedule = schedule;
    }

    public Date getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(Date scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public Date getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(Date scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
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

    public String getSchedulename() {
        return schedulename;
    }

    public void setSchedulename(String schedulename) {
        this.schedulename = schedulename;
    }

    public Long getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Long scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Float getPassScore() {
        return passScore;
    }

    public void setPassScore(Float passScore) {
        this.passScore = passScore;
    }

    public Integer getPromise() {
        return promise;
    }

    public void setPromise(Integer promise) {
        this.promise = promise;
    }
}
