package org.dreamer.examination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 记录参考人员的一次考试
 * @author xwang
 * @version 1.0
 *          ${tags}
 */
@Entity
@Table(name="v_examination_notpass")
public class ExaminationViewNotPassVO extends ExaminationViewBaseClass implements Serializable{

}
