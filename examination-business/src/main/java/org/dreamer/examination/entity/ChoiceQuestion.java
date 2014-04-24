package org.dreamer.examination.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

/**
 * 选择题
 * Created by lcheng on 14-3-9.
 */
@Entity
@DiscriminatorValue(value = "CH")
public class ChoiceQuestion extends Question {

    //单选还是多选
//    private boolean multiple;

    @OneToMany(fetch =FetchType.EAGER ,cascade ={CascadeType.PERSIST,CascadeType.REMOVE,
            CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "ques_id")
    @OrderBy(" orderNo ASC")
    List<QuestionOption> questionOptions;

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
