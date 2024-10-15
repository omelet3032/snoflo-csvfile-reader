package org.snoflo.entry;

import java.util.Scanner;

import org.snoflo.application.ApplicationStrategy;
import org.snoflo.controller.AppController;
import org.snoflo.system.AppSystem;

public class AppEntry implements AppController, ApplicationStrategy {

    private AppSystem finderSystem;
    private AppSystem questionSystem;

    private Scanner scanner;

    public AppEntry(ResourceManager resourceManager, AppSystem finderSystem, AppSystem questionSystem) {
        this.finderSystem = finderSystem;
        this.questionSystem = questionSystem;
        this.scanner = resourceManager.getScanner();
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
