package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
//@Entity
//@Table(name="v_examination_stu_not")
//@IdClass(NotParticipatePK.class)
public class StudentNotParticipateView implements Serializable {
    @Id
    private Long scheduleId;
    @Id
    @Column(name = "stu_id")
    private Long stuId;

    private String scheduleName;
    @Column(name = "stu_class_name")
    private String stuClassName;
    @Column(name = "stu_name")
    private String stuName;
    @Column(name = "stu_collage_id")
    private Long collegeId;
    @Column(name = "stu_collage")
    private String collegeName;
    @Column(name = "stu_professional")
    private String stuMajor;
    @Column(name = "stu_username")
    private String stuNo;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getStuClassName() {
        return stuClassName;
    }

    public void setStuClassName(String stuClassName) {
        this.stuClassName = stuClassName;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }
}
