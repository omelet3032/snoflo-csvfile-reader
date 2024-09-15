package org.snoflo.view;

public class MainView extends AppView {

    public void showPromptMainMenu() {
        System.out.println("---------------------------------");
        System.out.println("--- 자바로 구현하는 랜덤퀴즈 프로그램 시작 --- ");
        System.out.println("---------------------------------");
    }

    public void showSelectMenu() {
        System.out.println("--------------------------------");
        System.out.println("메뉴 선택");
        System.out.println("1. 전체 출력");
        System.out.println("2. id로 검색");
        System.out.println("3. 이름으로 검색");
        System.out.println("4. 랜덤 출력");
        System.out.println("5. 나가기");
        System.out.println("---------------------------------");
    }

   
}
