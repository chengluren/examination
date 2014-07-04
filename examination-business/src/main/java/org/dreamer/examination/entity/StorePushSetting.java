package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lcheng on 2014/7/3.
 */
@Entity
@Table(name="store_push_settings")
public class StorePushSetting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_STORE_PUSHS")
    @TableGenerator(name = "ID_STORE_PUSHS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private Long id;

    private Long collegeId;
    private Integer grade;
    private Types.DegreeType degree;
    private Integer pushType;

    public StorePushSetting(){}

    public StorePushSetting(Long collegeId,Integer grade,
                            Types.DegreeType degree,Integer pushType){
        this.collegeId = collegeId;
        this.grade = grade;
        this.degree = degree;
        this.pushType = pushType;
    }

    public StorePushSetting(Long collegeId,Integer grade,
                            Types.DegreeType degree){
        this(collegeId,grade,degree,0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Types.DegreeType getDegree() {
        return degree;
    }

    public void setDegree(Types.DegreeType degree) {
        this.degree = degree;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }
}
