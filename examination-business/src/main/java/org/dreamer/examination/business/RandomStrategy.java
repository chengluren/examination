package org.dreamer.examination.business;

import org.dreamer.examination.entity.TemplateQuestionDef;

import java.util.List;

/**
 *随机抽取试题的策略
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface RandomStrategy {

    List<Long>  randomGenerate(TemplateQuestionDef def);
}
