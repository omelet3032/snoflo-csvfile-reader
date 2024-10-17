package org.snoflo.application;

import org.snoflo.controller.AppController;
import org.snoflo.controller.EntryController;
import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;

public class Application {

    public void start() {

        ResourceManager resourceManager = new ResourceManager();

        FinderFactory finderFactory = new FinderFactory();
        FinderController finderController = finderFactory.getFinderController(resourceManager);

        QuestionFactory questionFactory = new QuestionFactory();
        QuestionController questionController = questionFactory.getQuestionController(resourceManager);

        AppController entryController = new EntryController(resourceManager, finderController, questionController);

        entryController.start();
        // resourceManager.close();
    }

}
