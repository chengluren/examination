package org.dreamer.examination.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private Map<Types.QuestionType,List<PaperQuestionVO>> paperQuestions;

    private Date createTime;

    //学生提交的答案
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String answers;

    public void quesIdsToTxt(){
        if (paperQuestions!=null){
           StringBuilder sb = new StringBuilder();
           for (Types.QuestionType type : paperQuestions.keySet()){
               List<PaperQuestionVO> typedIds = paperQuestions.get(type);
               //sb.append()
               for (PaperQuestionVO vo : typedIds){

               }
           }
        }
    }

    public void quesIdsToMap(){
        if (quesIdTxt!=null){

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

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public Map<Types.QuestionType, List<PaperQuestionVO>> getPaperQuestions() {
        return paperQuestions;
    }

    public void setPaperQuestions(Map<Types.QuestionType, List<PaperQuestionVO>> paperQuestions) {
        this.paperQuestions = paperQuestions;
    }
}
