package org.dreamer.examination.vo;

import java.io.Serializable;

/**
 * Created by lcheng on 2014/5/23.
 */
public class BaseInfoVO implements Serializable {

    public BaseInfoVO(){}

    public BaseInfoVO(Long id,String name){
        this.id = id;
        this.name = name;
    }

    private Long id;
    private String name;

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
}
