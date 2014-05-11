package org.dreamer.examination.vo;

import java.util.Map;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public class CommitAnswerVO {

    private Long examId;
    private Long paperId;

    private Map<Long,String> answers;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Map<Long, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long, String> answers) {
        this.answers = answers;
    }
}
