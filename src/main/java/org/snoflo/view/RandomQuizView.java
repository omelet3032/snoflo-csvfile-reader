package org.snoflo.view;

import java.util.List;


public class RandomQuizView implements AppView {

    public void showPromptRandomQuestion() {
        System.out.println("-----------------------------");
        System.out.println("----- 랜덤 퀴즈 시작 -----");
        System.out.println("-----------------------------");
    }

    public void showResultQuestionField(Object questionField) {
        System.out.println("-----------------------------");
        System.out.println("질문 : " + questionField);
    }

    public void showResultAnswerField(Object answerField) {
        System.out.println("정답 : " + answerField);
        System.out.println("-----------------------------");
    }

    public void showSelectAskPlay() {
        System.out.println("-----------------------------");
        System.out.println("----- 계속 진행하시겠습니까? -----");
        System.out.println("-----------------------------");
    }

    public void showSelectRegisterdFileMenu(List<String> fileList) {
        System.out.println("--------------------------------");
        System.out.println("등록된 파일 리스트");
        for (int i = 0; i < fileList.size(); i++) {
            System.out.println((i + 1) + ". " + fileList.get(i).toLowerCase());
        }
    }

}
