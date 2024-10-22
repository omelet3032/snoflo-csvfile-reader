package org.snoflo.domain;


public class Question {

    String question;

    String answer;

    public Question() {
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }


    public void convertRowToQuestion(String questionValue, String answerValue) {
        this.question = questionValue;
        this.answer = answerValue;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }


}
