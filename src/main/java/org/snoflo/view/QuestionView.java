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

    public void showSelectMenu() {
        System.out.println("--------------------------------");
        System.out.println("메뉴 선택");
        System.out.println("1. 퀴즈 시작");
        System.out.println("2. 전체 출력");
        System.out.println("3. 종료");
        System.out.println("---------------------------------");
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
        System.out.println("-----------------------------");
        System.out.println("질문 : " + questionField);
    }

    public void showResultAnswerField(Object answerField) {
        System.out.println("정답 : " + answerField);
        System.out.println("-----------------------------");
    }

    public void showPromptAskPlay() {
        System.out.println("-----------------------------");
        System.out.println("----- 계속 진행하시겠습니까? -----");
        System.out.println("-----------------------------");
        System.out.println("Y/n");

    }

    public void showPromptAskExit() {
        System.out.println("-----------------------------");
        System.out.println("----- 앱을 종료하시겠습니까? -----");
        System.out.println("-----------------------------");
        System.out.println("Y/n");
    }

    public void showSelectRegisterdFileMenu(List<String> fileList) {
        System.out.println("--------------------------------");
        System.out.println("등록된 파일 리스트");
        for (int i = 0; i < fileList.size(); i++) {
            System.out.println((i + 1) + ". " + fileList.get(i).toLowerCase());
        }
    }

}
