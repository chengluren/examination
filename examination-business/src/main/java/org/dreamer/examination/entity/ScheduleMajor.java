package org.dreamer.examination.entity;

import javax.persistence.*;

/**
 * Created by lcheng on 2014/5/26.
 */
@Entity
@Table(name="schedule_majors")
public class ScheduleMajor {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_SCHEDULE_MAJORS")
    @TableGenerator(name = "ID_SCHEDULE_MAJORS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private Long id;
    private Long scheduleId;
    private String majorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }
}
