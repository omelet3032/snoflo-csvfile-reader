package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.application.ResourceManager;
import org.snoflo.view.EntryView;

public class EntryController implements AppController {

    private FinderController finderController;
    private QuestionController questionController;

    private ResourceManager resourceManager;
    private EntryView mainView;

    public EntryController(ResourceManager resourceManager, FinderController finderController,
            QuestionController questionController) {
        this.resourceManager = resourceManager;
        this.finderController = finderController;
        this.questionController = questionController;
        this.mainView = new EntryView();
    }

    @Override
    public void start() {
        String answer;
        while (true) {
            mainView.showPromptMainMenu();
            mainView.showSelectStartMenu();
            answer = resourceManager.getScanner().nextLine();

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
                    mainView.showPromptCorrectNumber();
            }
        }
    }

    private void exitApp() {
        mainView.showPromptExitApp();
        resourceManager.close();
        System.exit(0);
    }

    // @Override
    // public void start() {

    // String answer;
    // mainView.showPromptMainMenu();
    // mainView.showSelectStartMenu();

    // while (true) {

    // answer = resourceManager.getScanner().nextLine();

    // if (answer.equals("1")) {
    // questionController.start();
    // } else if (answer.equals("2")) {
    // finderController.start();
    // } else if (answer.equals("3")) {
    // return;
    // } else {
    // System.out.println("1,2,3중 하나를 눌러주세요.");
    // }

    // }
    // }

}
