package org.snoflo.application;


public class ResourceHandler {

    private ResourceInitializer resourceInitializer;

    public ResourceHandler() {
        this.resourceInitializer = new ResourceInitializer();
    }

	public void connectH2WebConsole() {
        resourceInitializer.getConsole().connect();
	}

	public void closeResource() {
        resourceInitializer.getDataSource().close();
        resourceInitializer.getScanner().close();
        resourceInitializer.getConsole().stop();
	}

	public ResourceInitializer getResourceManager() {
		return this.resourceInitializer;
	}

    
}
