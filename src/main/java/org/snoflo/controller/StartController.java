package org.snoflo.controller;

import java.util.List;

import org.snoflo.view.MainView;

public class StartController extends AppController implements CommonControllerInterface {

    private MainView mainView;
    private FinderController finderController;
    private QuestionController questionController;

    public StartController(MainView mainView, FinderController finderController,
            QuestionController questionController) {
        this.mainView = mainView;
        this.finderController = finderController;
        this.questionController = questionController;
    }

    public void start() throws IllegalArgumentException, IllegalAccessException {

      
        while (true) {
            mainView.showSelectStartMenu();
            int number = Integer.parseInt(scanner.nextLine());

            switch (number) {
                case 1 -> startQuestionController();
                case 2 -> startFinderController();
                case 3 -> exitApp();
            }
        }
    }

    public void startFinderController() {
        finderController.start();
    }

    public void startQuestionController() throws IllegalArgumentException, IllegalAccessException {

        questionController.executeRandomQuestion();

    }

}
