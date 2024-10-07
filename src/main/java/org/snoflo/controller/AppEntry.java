package org.snoflo.controller;

import org.snoflo.view.MainView;

public class AppEntry extends AppController implements CommonControllerInterface {

    private MainView mainView;
    private FinderController finderController;
    private QuestionController questionController;

    public AppEntry(MainView mainView, FinderController finderController,
            QuestionController questionController) {
        this.mainView = mainView;
        this.finderController = finderController;
        this.questionController = questionController;
    }

}
