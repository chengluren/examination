package org.dreamer.examination.vo;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class StudentVO {

    private String college;
    private String major;
    private String className;
    private String name;
    private String stuId;

    public StudentVO(){}

    public StudentVO(String college, String major, String className, String name, String stuId) {
        this.college = college;
        this.major = major;
        this.className = className;
        this.name = name;
        this.stuId = stuId;
    }

    public StudentVO(Object[] stu){
        assert stu!=null && stu.length ==5;
        this.college = (String)stu[0];
        this.major = (String)stu[1];
        this.className = (String)stu[2];
        this.name = (String)stu[3];
        this.stuId = (String)stu[4];
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
