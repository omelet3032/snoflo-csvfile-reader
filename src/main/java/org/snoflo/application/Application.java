package org.snoflo.application;

import org.snoflo.commander.AppCommander;
import org.snoflo.commander.CustomContext;

public class Application {

    public void start() {

        AppCommander entryCommander = new AppCommanderFactory().createEntryCommander();
        // entryCommander.executeCommander();
    
        CustomContext context = new CustomContext();
        context.runContext(entryCommander);
    }


}
