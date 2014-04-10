package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 试题
 * Created by lcheng on 14-3-9.
 */
@Entity
@Table(name = "questions")
@Inheritance
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "questions")
@DiscriminatorColumn(name = "QUES_TYPE")
public class Question implements Serializable {

    public enum Difficulty {
        Easy, Moderate, DIFFICULT
    }

    public Question() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_QUES")
    @TableGenerator(name = "ID_QUES", table = "ids_gen", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VALUE", initialValue = 1)
    private long id;

    //题干
    @Column(nullable = false, length = 300)
    private String stem;
    //难易程度
    @Enumerated
    private Difficulty difficulty;
    @Column(length = 100)
    private String answer;
    //题目对应的图片地址
    @Column(length = 150)
    private String imgPath;
    //对应的题库Id
    @Column(nullable = false)
    private Long store;

    //是否是必考题的标识
    private boolean mustChoose;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Long getStore() {
        return store;
    }

    public void setStore(Long store) {
        this.store = store;
    }

    public boolean isMustChoose() {
        return mustChoose;
    }

    public void setMustChoose(boolean mustChoose) {
        this.mustChoose = mustChoose;
    }
}
