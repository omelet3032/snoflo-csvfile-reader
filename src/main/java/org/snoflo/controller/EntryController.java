package org.snoflo.controller;

import java.util.Scanner;
import java.util.logging.Handler;

import org.snoflo.application.ResourceHandler;
import org.snoflo.view.EntryView;

public class EntryController {

    private CsvFileRegisterController finderController;
    private QuestionController questionController;

    private CustomHandler handler;

    private EntryView entryView;

    private Scanner scanner;

    private ResourceHandler resourceHandler;

    // public EntryController(ResourceHandler resourceHandler, Scanner scanner,
    // CsvFileRegisterController finderController,
    // QuestionController questionController) {
    // this.finderController = finderController;
    // this.questionController = questionController;
    // this.entryView = new EntryView();
    // this.scanner = scanner;
    // this.resourceHandler = resourceHandler;
    // }

    public EntryController(ResourceHandler resourceHandler, Scanner scanner, CustomHandler handler,
            QuestionController questionController) {
        this.handler = handler;
        this.questionController = questionController;
        this.entryView = new EntryView();
        this.scanner = scanner;
        this.resourceHandler = resourceHandler;
    }

    public void start() {

        resourceHandler.connectH2WebConsole();

        String answer;
        while (true) {
            entryView.showPromptMainMenu();
            entryView.showSelectStartMenu();
            answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    questionController.start();
                    break;
                case "2":
                    // finderController.start(); // 이 부분에 핸들러 클래스가 들어와야 한다.
                    handler.executeHandler();
                    break;
                case "3":
                    exitApp();
                default:
                    entryView.showPromptCorrectNumber();
            }
        }
    }

    private void exitApp() {
        entryView.showPromptExitApp();
        resourceHandler.closeResource();
        System.exit(0);
    }

}
