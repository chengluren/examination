package org.dreamer.examination.vo;

import java.util.List;

/**
 * Created by lcheng on 2014/5/11.
 */
public class MajorVO {

    private Long id;
    private String name;
    //private String state;
    private boolean open;

    private boolean nocheck;

    private List<MajorVO> children;

    public MajorVO() {
    }

    public MajorVO(Long id,String name){
        this.id = id;
        this.name = name;
    }

    public MajorVO(Long id,String name,boolean open){
        this(id,name);
        this.open = open;
    }

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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<MajorVO> getChildren() {
        return children;
    }

    public void setChildren(List<MajorVO> children) {
        this.children = children;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }
}
