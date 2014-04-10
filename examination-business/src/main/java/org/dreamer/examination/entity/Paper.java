package org.dreamer.examination.entity;

import com.google.common.base.Joiner;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * 学生的考试试卷
 *
 * @author lcheng
 * @version 1.0
 */
@Entity
@Table(name = "papers")
public class Paper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_PAPERS")
    @TableGenerator(name = "ID_PAPERS", table = "ids_gen", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VALUE", initialValue = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "temp_id")
    private ExamTemplate template;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String quesIdTxt;

    @Transient
    private Map<Types.QuestionType, List<PaperQuestionVO>> paperQuestions;

    private Date createTime;

//    //学生提交的答案
//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    private String answers;

    public void quesIdsToTxt() {
        if (paperQuestions != null) {
            StringBuilder sb = new StringBuilder();
            for (Types.QuestionType type : paperQuestions.keySet()) {
                List<PaperQuestionVO> typedIds = paperQuestions.get(type);
                for (PaperQuestionVO vo : typedIds) {
                    if (vo.getId() != null) {
                        sb.append(vo.getId());
                    } else if (vo.getIds() != null && vo.getIds().size() > 0) {
                        Joiner.on(",").appendTo(sb, vo.getIds());
                    }
                    sb.append(",");
                    sb.append(vo.getScore());
                    sb.append("|");
                }
                sb.append(type.getShortName() + "/");
            }
            this.quesIdTxt = sb.toString();
        }
    }

    public void quesIdsToMap() {
        if (quesIdTxt != null) {
            String[] typedStrArr = quesIdTxt.split("/");
            Map<Types.QuestionType, List<PaperQuestionVO>> result = new HashMap<>();
            for (String typedStr : typedStrArr) {
                String[] quesArr = typedStr.split("|");
                String strType = quesArr[quesArr.length - 1];
                Types.QuestionType type = Types.QuestionType.getTypeFromShortName(strType);
                result.put(type, new ArrayList<PaperQuestionVO>());
                for (int i = 0; i < quesArr.length - 1; i++) {
                    String q = quesArr[i];
                    String[] detail = q.split(",");
                    float score = Float.valueOf(detail[detail.length - 1]);
                    if (detail.length == 2) {
                        Long id = Long.valueOf(detail[0]);
                        result.get(type).add(new PaperQuestionVO(id, score));
                    } else if (detail.length > 2) {
                        List<Long> ids = new ArrayList<>();
                        for (int j = 0; j < detail.length - 1; j++) {
                            Long id = Long.valueOf(detail[j]);
                            ids.add(id);
                        }
                        result.get(type).add(new PaperQuestionVO(ids, score));
                    }
                }
            }
            this.paperQuestions = result;
        }
    }

    //==================getter and setter==============================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ExamTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ExamTemplate template) {
        this.template = template;
    }

    public String getQuesIdTxt() {
        return quesIdTxt;
    }

    public void setQuesIdTxt(String quesIdTxt) {
        this.quesIdTxt = quesIdTxt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

//    public String getAnswers() {
//        return answers;
//    }
//
//    public void setAnswers(String answers) {
//        this.answers = answers;
//    }

    public Map<Types.QuestionType, List<PaperQuestionVO>> getPaperQuestions() {
        return paperQuestions;
    }

    public void setPaperQuestions(Map<Types.QuestionType, List<PaperQuestionVO>> paperQuestions) {
        this.paperQuestions = paperQuestions;
    }
}
