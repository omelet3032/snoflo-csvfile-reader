package org.snoflo.application;

import org.snoflo.commander.AppCommander;

public class Application {

    public void start() {

        AppCommander entryCommander = new AppCommanderFactory().creatEntryCommander();
        entryCommander.executeCommander();
    }

}
