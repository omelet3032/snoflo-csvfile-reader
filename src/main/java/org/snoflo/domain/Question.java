package org.snoflo.domain;

import org.snoflo.dto.QuestionDto;

public class Question {

    String question;

    String answer;

    public Question() {
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public QuestionDto toDto(String questionValue, String answerValue) {
        return new QuestionDto(questionValue, answerValue);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
