package org.dreamer.examination.entity;

import javax.persistence.*;

/**
 * Created by lcheng on 2014/5/7.
 */
@Entity
@Table(name = "jiaoda_majors")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_MAJORS")
    @TableGenerator(name = "ID_MAJORS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private Long id;

    @Column(length = 80)
    private String name;

    @Column(length = 50)
    private String abbrName;

    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
