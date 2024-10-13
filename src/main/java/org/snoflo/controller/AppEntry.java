package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.application.ApplicationStrategy;
import org.snoflo.system.AppSystem;
import org.snoflo.system.FinderSystem;
import org.snoflo.system.QuestionSystem;
import org.snoflo.view.MainView;

public class AppEntry implements ApplicationStrategy {


    private AppSystem finderSystem;
    private AppSystem questionSystem;

    private Scanner scanner;

    public AppEntry(Scanner scanner, AppSystem finderSystem, 
            AppSystem questionSystem) {
        this.finderSystem = finderSystem;
        this.questionSystem = questionSystem;
        this.scanner = scanner;
    }

    @Override
    public void start() {
        
        boolean isRunning = true;
        while (isRunning) {
            showPromptMainMenu();
            showSelectStartMenu();
            int number = Integer.parseInt(scanner.nextLine());
            
            switch (number) {
                case 1 -> questionSystem.startSystem(); 
                case 2 -> finderSystem.startSystem();
                case 3 -> isRunning = false;
            }
        }
        return;
    }

     private void showPromptMainMenu() {
        System.out.println("---------------------------------");
        System.out.println("--- 자바로 구현하는 랜덤퀴즈 프로그램 --- ");
        System.out.println("---------------------------------");
    }


    private void showSelectStartMenu() {
        System.out.println("--------------------------------");
        System.out.println("1. 시작하기");
        System.out.println("2. 파일 등록하기");
        System.out.println("3. 종료");
        System.out.println("---------------------------------");
    }

}
