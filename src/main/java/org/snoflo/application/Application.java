package org.snoflo.application;

import java.io.IOException;
import java.util.Scanner;

import org.snoflo.controller.AppController;
import org.snoflo.controller.EntryController;
import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.factory.FinderFactory;
import org.snoflo.factory.QuestionFactory;

public class Application {

    public void start() {

        ResourceManager resourceManager = new ResourceManager();

        // FinderController finderController = (FinderController) new FinderFactory().createOperation(resourceManager);
        // QuestionController questionController = (QuestionController) new QuestionFactory()
        //         .createOperation(resourceManager);

        FinderFactory2 finderController2 = new FinderFactory2();
        finderController2.getFinderController(resourceManager);

        QuestionFactory2 questionController2 = new QuestionFactory2();
        questionController2.getQuestionController(resourceManager);

        AppController entryController = new EntryController(resourceManager, finderController, questionController);

        entryController.start();
        // resourceManager.close();
    }

}
