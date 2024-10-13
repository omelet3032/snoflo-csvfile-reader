package org.snoflo.factory;

import org.snoflo.builder.CommonNestedBuilder;
import org.snoflo.builder.AppSystemBuilder;
import org.snoflo.builder.FinderSystemBuilder;
import org.snoflo.system.AppSystem;
import org.snoflo.system.FinderSystem;

public interface AppFactory {

    public AppSystem createSystem();
}
