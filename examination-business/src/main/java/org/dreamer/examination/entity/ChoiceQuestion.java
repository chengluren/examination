package org.dreamer.examination.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

/**
 * 选择题
 * Created by lcheng on 14-3-9.
 */
@Entity
@DiscriminatorValue(value = "CHOICE")
public class ChoiceQuestion extends Question {


    List<QuestionOption> questionOptions;
}
