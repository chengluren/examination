package org.dreamer.examination.entity;

import java.io.Serializable;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class NotParticipatePK implements Serializable {

    private Long scheduleId;
    private Long stuId;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result + getScheduleId().intValue();
        result = 31*result + getStuId().intValue();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NotParticipatePK))
            return false;
        if (obj==this)
            return true;
        NotParticipatePK that = (NotParticipatePK)obj;
        return (that.getScheduleId().longValue()==getScheduleId().longValue()) && (that.getStuId().longValue() == getStuId().longValue());
    }
}
