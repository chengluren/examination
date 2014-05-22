package org.dreamer.examination.entity;


import javax.persistence.*;
import java.util.List;

/**
 * Created by lcheng on 2014/5/7.
 */
@Entity
@Table(name = "jiaoda_colleges")
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_COLLEGES")
    @TableGenerator(name = "ID_COLLEGES", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private Long id;

    @Column(length = 80)
    private String name;

    @OneToMany(mappedBy = "college",fetch = FetchType.EAGER)
    private List<Major> majors;

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

    public List<Major> getMajors() {
        return majors;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }
}
