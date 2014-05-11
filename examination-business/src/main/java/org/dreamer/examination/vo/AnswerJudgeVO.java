package org.dreamer.examination.vo;

import org.dreamer.examination.entity.Types;

/**
 * Created by lcheng on 2014/4/17.
 */
public class AnswerJudgeVO {

    private long quesId;
    private String answer;
    private String realAnswer;
    private float score;
    private Types.QuestionType questionType;

    public AnswerJudgeVO(long quesId, String answer, String realAnswer, float score, Types.QuestionType questionType) {
        this.quesId = quesId;
        this.answer = answer;
        this.realAnswer = realAnswer;
        this.score = score;
        this.questionType = questionType;
    }

    public long getQuesId() {
        return quesId;
    }

    public void setQuesId(long quesId) {
        this.quesId = quesId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRealAnswer() {
        return realAnswer;
    }

    public void setRealAnswer(String realAnswer) {
        this.realAnswer = realAnswer;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Types.QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Types.QuestionType questionType) {
        this.questionType = questionType;
    }
}
