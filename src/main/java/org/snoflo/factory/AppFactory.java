package org.snoflo.factory;

import org.snoflo.application.ResourceManager;
import org.snoflo.controller.AppController;

public abstract class AppFactory {

    public final AppController createOperation(ResourceManager resourceManager) {
        AppController controller = createProduct(resourceManager);

        return controller;
    }

    abstract public AppController createProduct(ResourceManager resourceManager);

}
