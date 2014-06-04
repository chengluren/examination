package org.dreamer.examination.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 考试日程安排
 * @author lcheng
 * @version 1.0
 *
 */
@Entity
@Table(name = "exam_schedules")
public class ExamSchedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_EXAM_SCHEDULES")
    @TableGenerator(name = "ID_EXAM_SCHEDULES", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private long id;
    //本次考试针对的考生入学年份，也即多少界
    private int admissionYear;
    //本次考试针对的考生学历，是本科生还是研究生
    @Enumerated
    private Types.DegreeType degree;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "TEMP_ID")
    private ExamTemplate template;

    //本次考试针对的专业
    //private String major;
    //本次考试对应的专业名字
    @Lob
    private String majorNames;
    //考试名称
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ExamTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ExamTemplate template) {
        this.template = template;
    }

//    public String getMajor() {
//        return major;
//    }
//
//    public void setMajor(String major) {
//        this.major = major;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }

    public Types.DegreeType getDegree() {
        return degree;
    }

    public void setDegree(Types.DegreeType degree) {
        this.degree = degree;
    }

    public String getMajorNames() {
        return majorNames;
    }

    public void setMajorNames(String majorNames) {
        this.majorNames = majorNames;
    }
}
