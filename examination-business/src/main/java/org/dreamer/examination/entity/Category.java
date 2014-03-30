package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 题库分类
 * Created by lcheng on 14-3-30.
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    public Category(){}

    @Id()
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_CATEGORIES")
    @TableGenerator(name = "ID_CATEGORIES", table = "ids_gen", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VALUE", initialValue = 1)
    private long id;

    @Column(length = 50)
    private long name;


}
