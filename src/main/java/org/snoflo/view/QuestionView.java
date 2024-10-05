package org.snoflo.view;

import java.util.List;

import org.snoflo.domain.Question;

public class QuestionView {

    public void showResultFindAll(List<Question> list) {
        System.out.println("--------------------------------");
        System.out.println();
        for (Question question : list) {
            System.out.println(question.toString());
        }
    }

    public void showPromptFindById() {
        System.out.println("-----------------------------");
        System.out.println("----- id로 검색하기 -----");
        System.out.println("-----------------------------");
    }

    public void showResultFindById(Question concept) {
        System.out.println();
        System.out.println("conceptById : " + concept);
    }

    public void showPromptRandomQuestion() {
        System.out.println("-----------------------------");
        System.out.println("----- 랜덤 퀴즈 시작 -----");
        System.out.println("-----------------------------");
    }

    public void showResultQuestionField(Object questionField) {
        System.out.println("질문 : " + questionField);
    }

    public void showResultAnswerField(Object answerField) {
        System.out.println("정답 : " + answerField);
    }

    public void showPromptAskExit() {
        System.out.println("-----------------------------");
        System.out.println("----- 앱을 종료하시겠습니까? -----");
        System.out.println("-----------------------------");
        System.out.println("Y/n");
    }

}
