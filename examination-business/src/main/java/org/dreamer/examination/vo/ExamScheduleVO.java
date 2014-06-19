package org.dreamer.examination.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class ExamScheduleVO implements Serializable {

    private String name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;
    private Long scheduleId;
    private Integer examTimeSpan;

    public ExamScheduleVO() {
    }

    public ExamScheduleVO(String name, Date startDate, Date endDate, Long scheduleId,Integer examTimeSpan) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.scheduleId = scheduleId;
        this.examTimeSpan = examTimeSpan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getExamTimeSpan() {
        return examTimeSpan;
    }

    public void setExamTimeSpan(Integer examTimeSpan) {
        this.examTimeSpan = examTimeSpan;
    }
}
