package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.application.ApplicationStrategy;
import org.snoflo.view.MainView;

public class StartController implements ApplicationStrategy, AppController {

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

    @Override
    public void start() {
        
        boolean isRunning = true;
        while (isRunning) {
            mainView.showPromptMainMenu();
            mainView.showSelectStartMenu();
            int number = Integer.parseInt(scanner.nextLine());
            
            // switch (number) {
            //     case 1 -> startQuestionController();
            //     case 2 -> startFinderController();
            //     // case 3 -> exitApp();
            //     case 3 -> isRunning = false;
            // }
    
            switch (number) {
                case 1 -> startQuestionController();
                case 2 -> startFinderController();
                // case 3 -> exitApp();
                case 3 -> isRunning = false;
            }
        }
        return;
    }

    private void startFinderController() {
        finderController.start();
    }

    private void startQuestionController() {
        questionController.start();
    }

    public void exitApp() {
        System.exit(0);
    }

}
