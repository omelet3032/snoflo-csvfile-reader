package org.snoflo.controller;

public interface MainController {

    default void exitApp() {
        System.exit(0);
    }

    default void startFinderController() {

    }

    default void startQuestionController() {
        
    }
}
