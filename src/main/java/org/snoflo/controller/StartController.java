package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.view.MainView;

public class StartController {

    private MainView mainView;
    private FinderController finderController;
    private QuestionController questionController;

    private Scanner scanner;

    public StartController(Scanner scanner, MainView mainView, FinderController finderController,
            QuestionController questionController) {
        this.mainView = mainView;
        this.finderController = finderController;
        this.questionController = questionController;
        this.scanner = scanner;
    }

    public void start() throws IllegalArgumentException, IllegalAccessException {

        while (true) {
            mainView.showPromptMainMenu();
            mainView.showSelectStartMenu();
            int number = Integer.parseInt(scanner.nextLine());

            switch (number) {
                case 1 -> startQuestionController();
                case 2 -> startFinderController();
                case 3 -> exitApp();
            }
        }
    }

    private void startFinderController() {
        finderController.start();
    }

    private void startQuestionController() throws IllegalArgumentException, IllegalAccessException {
        questionController.executeRandomQuestion();
    }

    private void exitApp() {
        System.exit(0);
    }

}
