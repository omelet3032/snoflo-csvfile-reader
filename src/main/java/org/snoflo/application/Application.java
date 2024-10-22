package org.snoflo.application;

import org.snoflo.strategy.AppStrategy;
import org.snoflo.factory.StrategyFactory;
import org.snoflo.strategy.AppContext;

public class Application {

    public void start() {

        AppContext context = new AppContext();
        AppStrategy entryStrategy = new StrategyFactory(context).createEntryStrategy();
    
        context.runContext(entryStrategy);
    }


}
