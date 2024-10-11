package org.snoflo.factory;

import org.snoflo.builder.AppControllerBuilder;
import org.snoflo.builder.FinderControllerBuilder;

public interface AppFactory {

    public AppFactory createComponent();

    public AppControllerBuilder createBuilder();

}
