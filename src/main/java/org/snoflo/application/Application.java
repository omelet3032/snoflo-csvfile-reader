package org.snoflo.application;

import org.snoflo.controller.EntryController;

public class Application {

    public void start() {

        // SupportHandler handler = new SupportHandler();

        EntryControllerFactory entryControllerFactory = new EntryControllerFactory();
        EntryController entryController = entryControllerFactory.createEntryController();
        entryController.start();
    }

}
