package org.dreamer.examination.entity;

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

    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "TEMP_ID")
    private ExamTemplate template;

    //本次考试针对的专业
    private String major;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

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
}
