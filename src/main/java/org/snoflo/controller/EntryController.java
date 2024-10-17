package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.application.ResourceHandler;
import org.snoflo.application.ResourceInitializer;
import org.snoflo.view.EntryView;

public class EntryController {

    private FinderController finderController;
    private QuestionController questionController;

    private EntryView entryView;

    private Scanner scanner;

    private ResourceHandler resourceHandler;

    public EntryController(ResourceHandler resourceHandler, Scanner scanner, FinderController finderController,
            QuestionController questionController) {
        this.finderController = finderController;
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
                    finderController.start();
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
