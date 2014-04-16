package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 考试方案模板
 * Created by lcheng on 2014/4/1.
 */
@Entity
@Table(name = "exam_templates")
public class ExamTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_EXAM_TEMPS")
    @TableGenerator(name = "ID_EXAM_TEMPS", table = "gen_ids", pkColumnName = "id_name",
            valueColumnName = "id_value", initialValue = 1)
    private long id;

    @Column(length = 80)
    private String name;

    //必考题中按题型分类的必考题
    @OneToMany(mappedBy = "template",cascade = {CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @OrderBy("questionType asc")
    List<MustChooseQuestionDef> mustChooseDefs;

    @OneToMany(mappedBy = "template",
            cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @OrderBy("questionType asc")
    private List<TemplateQuestionDef> questionDefs;

    @Column(scale = 1)
    private float passScore;

    //是否将多选题混入选择题中，而不把多选题当做单独考试题型
    private boolean multiChoiceMixedInChoice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MustChooseQuestionDef> getMustChooseDefs() {
        return mustChooseDefs;
    }

    public void setMustChooseDefs(List<MustChooseQuestionDef> mustChooseDefs) {
        this.mustChooseDefs = mustChooseDefs;
    }

    public List<TemplateQuestionDef> getQuestionDefs() {
        return questionDefs;
    }

    public void setQuestionDefs(List<TemplateQuestionDef> questionDefs) {
        this.questionDefs = questionDefs;
    }

    public float getPassScore() {
        return passScore;
    }

    public void setPassScore(float passScore) {
        this.passScore = passScore;
    }

    public boolean isMultiChoiceMixedInChoice() {
        return multiChoiceMixedInChoice;
    }

    public void setMultiChoiceMixedInChoice(boolean multiChoiceMixedInChoice) {
        this.multiChoiceMixedInChoice = multiChoiceMixedInChoice;
    }
}
