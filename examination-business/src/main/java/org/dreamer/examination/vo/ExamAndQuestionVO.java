package org.dreamer.examination.vo;

import java.util.Date;
import java.util.List;

/**
 * 考试及试题信息
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class ExamAndQuestionVO {

    private long examId;
    private long paperId;
    private List<String> quesTypes;
    private List<ExamQuestionVO> questions;
    private int quesTotalCount;
    private Date startTime;
    private Date endTime;

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public long getPaperId() {
        return paperId;
    }

    public void setPaperId(long paperId) {
        this.paperId = paperId;
    }

    public List<ExamQuestionVO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ExamQuestionVO> questions) {
        this.questions = questions;
    }

    public List<String> getQuesTypes() {
        return quesTypes;
    }

    public void setQuesTypes(List<String> quesTypes) {
        this.quesTypes = quesTypes;
    }

    public int getQuesTotalCount() {
        return quesTotalCount;
    }

    public void setQuesTotalCount(int quesTotalCount) {
        this.quesTotalCount = quesTotalCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
