package org.snoflo.application;

public interface AppFactory {
    
    // public AppFactory createBuilder();
    public FinderBuilder createBuilder();

    public AppFactory createComponent();
    // public FinderBuilder createComponent();
}
