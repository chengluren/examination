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
public class ExamTemplate implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_EXAM_TEMPS")
    @TableGenerator(name = "ID_EXAM_TEMPS", table = "ids_gen", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VALUE", initialValue = 1)
    private long id;

    //必考题中按题型分类的必考题
    private Map<String,List<Long>> mustchoose;

    @OneToMany(mappedBy = "template")
    private List<TemplateQuestionRule> questionRules;
}
