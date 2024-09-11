package org.snoflo.view;

import org.snoflo.model.Question;

public class AppView {

    public void showPromptMainMenu() {
        System.out.println("---------------------------------");
        System.out.println("퀴즈 시작");
        System.out.println("---------------------------------");
    }

    public void showSelectMenu() {
        System.out.println("---------------------------------");
        System.out.println("메뉴 선택");
        System.out.println("1. id로 검색");
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


}
