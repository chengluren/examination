package org.dreamer.examination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 记录参考人员的一次考试
 * @author xwang
 * @version 1.0
 *          ${tags}
 */
@Entity
@Table(name="v_examination_passrate")
public class ExaminationViewPassRateVO implements Serializable{
    @Id
    private long id;

    @Column(name = "schedulename")
    private String schedulename;

    @Column(name = "scheduleid")
    private Long scheduleid;

    @Column(name = "major")
    private String major;

    @Column(name = "passrate")
    private String passrate;

    @Column(name = "avgscore")
    private Double avgscore;

    @Column(name = "maxscore")
    private Double maxscore;

    @Column(name = "minscore")
    private Double minscore;

    @Column(name = "excellentrate")
    private String excellentrate;

    @Column(name = "studentcount")
    private Long studentcount;



    public Double getMaxscore() {
        return maxscore;
    }

    public void setMaxscore(Double maxscore) {
        this.maxscore = maxscore;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPassrate() {
        return passrate;
    }

    public void setPassrate(String passrate) {
        this.passrate = passrate;
    }

    public Double getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(Double avgscore) {
        this.avgscore = avgscore;
    }

    public Double getMinscore() {
        return minscore;
    }

    public void setMinscore(Double minscore) {
        this.minscore = minscore;
    }

    public String getExcellentrate() {
        return excellentrate;
    }

    public void setExcellentrate(String excellentrate) {
        this.excellentrate = excellentrate;
    }

    public Long getStudentcount() {
        return studentcount;
    }

    public void setStudentcount(Long studentcount) {
        this.studentcount = studentcount;
    }
}
