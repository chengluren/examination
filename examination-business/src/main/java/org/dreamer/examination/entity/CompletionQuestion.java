package org.dreamer.examination.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 填空题
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
@Entity
@DiscriminatorValue(value = "CO")
public class CompletionQuestion extends Question{
}
