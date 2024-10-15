package org.snoflo.system;

import org.snoflo.builder.AppSystemBuilder;
import org.snoflo.builder.FinderSystemBuilder;
import org.snoflo.controller.AppController;
import org.snoflo.controller.FinderController;
import org.snoflo.factory.FinderFactory;

public class FinderSystem implements AppSystem {

    private AppController finderController;

    public FinderSystem(FinderSystemBuilder builder) {
        this.finderController = builder.getController();
    }


    
    @Override
    public void startSystem() {
        finderController.start();
    }



	public AppController getFinderController() {
		return this.finderController;
	}
}
