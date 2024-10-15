package org.snoflo.factory;


import org.snoflo.entry.ResourceManager;
import org.snoflo.system.AppSystem;


public abstract class AppFactory {

    public final AppSystem createOperation(ResourceManager resourceManager) {
        AppSystem system = createProduct(resourceManager);
         
        return system;
    }

   abstract protected AppSystem createProduct(ResourceManager resourceManager);

   abstract protected void initialize(ResourceManager resourceManager);
}
