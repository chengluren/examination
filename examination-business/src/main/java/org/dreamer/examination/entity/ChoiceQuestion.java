package org.dreamer.examination.entity;

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

    @OneToMany(fetch =FetchType.EAGER ,cascade ={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinTable(name = "question_options",
            joinColumns = @JoinColumn(name = "QUES_ID"),
            inverseJoinColumns = @JoinColumn(name = "OPT_ID")
    )
    @OrderBy(" orderNo ASC")
    List<QuestionOption> questionOptions;


//    public boolean isMultiple() {
//        return multiple;
//    }
//
//    public void setMultiple(boolean multiple) {
//        this.multiple = multiple;
//    }

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
