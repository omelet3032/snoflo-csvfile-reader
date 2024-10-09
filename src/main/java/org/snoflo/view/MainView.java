package org.snoflo.view;

import java.util.List;

public class MainView {

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

    public void showSelectRegisterdFileMenu(List<String> flieList) {
        System.out.println("--------------------------------");
        // for (String file : flieList) {
        //     System.out.println(file);
        // }
        System.out.println("등록된 파일 리스트");
        for (int i = 0; i < flieList.size(); i++) {
            System.out.println((i+1) + ". " + flieList.get(i).toLowerCase());
        }

        // System.out.println("1. 시작하기");
        // System.out.println("2. 파일 등록하기");
        // System.out.println("3. 종료");
        // System.out.println("---------------------------------");
    }
}
