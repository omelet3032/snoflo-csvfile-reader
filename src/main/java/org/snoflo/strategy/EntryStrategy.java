package org.snoflo.strategy;

import org.snoflo.controller.EntryController;
import org.snoflo.factory.ResourceHandler;

public class EntryStrategy implements AppStrategy {
    
    private EntryController entryController;

    private ResourceHandler resourceHandler;

	public EntryStrategy(EntryController entryController, ResourceHandler resourceHandler) {
		this.entryController = entryController;
        this.resourceHandler = resourceHandler;
	}

	@Override
	public void runStrategy() {
        this.resourceHandler.connectH2WebConsole();

        this.entryController.start();

		this.resourceHandler.closeResource();
        
        System.exit(0);
	}

    

}
