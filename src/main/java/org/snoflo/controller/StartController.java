package org.snoflo.controller;

import org.snoflo.view.MainView;

public class StartController extends AppController implements MainController {

    private MainView mainView;
    private FinderController finderController;
    private QuestionController questionController;

    public StartController(MainView mainView, FinderController finderController, QuestionController questionController) {
        this.mainView = mainView;
        this.finderController = finderController;
        this.questionController = questionController;
    }

    public void start() throws IllegalArgumentException, IllegalAccessException {
        mainView.showSelectStartMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1 -> questionController.start();
            case 2 -> finderController.start();
            case 3 -> exitApp();
            // default ->
        }
    }
}
