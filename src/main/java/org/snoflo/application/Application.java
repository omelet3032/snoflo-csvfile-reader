package org.snoflo.application;

import org.snoflo.strategy.AppStrategy;
import org.snoflo.strategy.AppContext;

public class Application {

    public void start() {

        AppStrategy entryStrategy = new AppCommanderFactory().createEntryCommander();
        // entryCommander.executeCommander();
    
        AppContext context = new AppContext();
        context.runContext(entryStrategy);
    }


}
