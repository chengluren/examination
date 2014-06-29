package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 专业和题库的对应关系。一个专业可以有多个题库
 * Created by lcheng on 2014/4/1.
 */
@Entity
@Table(name = "major_store_rel")
public class MajorStoreRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_MAJOR_STORE")
    @TableGenerator(name = "ID_MAJOR_STORE", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private long id;

    /**
     * 专业标识
     */
    //@Column(length = 30)
    private long major;

    private long storeId;

    public MajorStoreRelation() {
    }

    public MajorStoreRelation(long storeId, long major) {
        this.storeId = storeId;
        this.major = major;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMajor() {
        return major;
    }

    public void setMajor(long major) {
        this.major = major;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }
}
