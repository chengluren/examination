package org.dreamer.examination.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author lcheng
 * @version 1.0
 */
@Entity
@Table(name="v_examination_pass")
public class ExaminationViewPassVO extends ExaminationViewBaseClass implements Serializable {
}
