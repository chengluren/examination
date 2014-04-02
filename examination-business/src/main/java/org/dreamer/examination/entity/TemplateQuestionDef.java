package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 考试模板配制中，各题库试题的生成规则
 * Created by lcheng on 2014/4/1.
 */
@Entity
@Table(name = "temp_ques_rules")
public class TemplateQuestionDef implements Serializable{

    @Id()
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_QUES_DEFS")
    @TableGenerator(name = "ID_QUES_DEFS", table = "ids_gen", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VALUE", initialValue = 1)
    private long id;

    private long storeId;

    @Enumerated(EnumType.STRING)
    private Types.QuestionType questionType;

    private int count;

    @Column(scale=1)
    private float scorePer;

    @ManyToOne
    @JoinColumn(name = "TEMP_ID")
    private ExamTemplate template;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public Types.QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Types.QuestionType questionType) {
        this.questionType = questionType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getScorePer() {
        return scorePer;
    }

    public void setScorePer(float scorePer) {
        this.scorePer = scorePer;
    }
}
