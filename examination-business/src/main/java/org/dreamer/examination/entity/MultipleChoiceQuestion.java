package org.dreamer.examination.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Entity
@DiscriminatorValue(value = "MC")
public class MultipleChoiceQuestion extends ChoiceQuestion{
}
