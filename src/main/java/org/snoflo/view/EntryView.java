package org.snoflo.view;

public class EntryView implements AppView {

    public void showPromptMainMenu() {
        System.out.println("---------------------------------");
        System.out.println("--- 자바로 구현하는 랜덤퀴즈 프로그램 --- ");
        System.out.println("---------------------------------");
    }

    public void showSelectStartMenu() {
        System.out.println("--------------------------------");
        System.out.println("1. 시작하기");
        System.out.println("2. 파일 등록하기");
        System.out.println("3. 종료");
        System.out.println("---------------------------------");
    }

    public void showPromptCorrectNumber() {
        System.out.println("---------------------------------");
        System.out.println("1,2,3중 하나를 입력해주세요.");
        System.out.println("---------------------------------");
    }

    public void showAskExitApp() {
        System.out.println("---------------------------------");
        System.out.println("앱을 종료하시겠습니까?");
        System.out.println("---------------------------------");
    }

    public void showPromptExitApp() {
        System.out.println("---------------------------------");
        System.out.println("앱을 종료합니다.");
        System.out.println("---------------------------------");
    }

}
