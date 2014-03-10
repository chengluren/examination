package org.dreamer.examination.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 选择题
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Entity
@DiscriminatorValue(value = "TF")
public class TrueOrFalseQuestion extends Question{
}
