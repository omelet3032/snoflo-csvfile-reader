package org.snoflo.view;

public interface AppView {
    
    default void showPromptReturnMainMenu() {
        System.out.println("---------------------------------");
        System.out.println("메인 메뉴로 돌아갑니다.");
        System.out.println("---------------------------------");
    }

    default void showSelectYorN() {
        System.out.println("---------------------------------");
        System.out.println("Y/n중 하나를 입력해주세요.");
        System.out.println("---------------------------------");
    }
}
