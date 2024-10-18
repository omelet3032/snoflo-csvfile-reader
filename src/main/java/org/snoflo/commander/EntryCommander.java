package org.snoflo.commander;

import org.snoflo.application.ResourceHandler;
import org.snoflo.controller.EntryController;

public class EntryCommander implements AppCommander {
    
    private EntryController entryController;

    private ResourceHandler resourceHandler;

	public EntryCommander(EntryController entryController, ResourceHandler resourceHandler) {
		this.entryController = entryController;
        this.resourceHandler = resourceHandler;
	}

	@Override
	public void executeCommander() {
        resourceHandler.connectH2WebConsole();
        this.entryController.start();
		resourceHandler.closeResource();
        System.exit(0);
	}

    

}
