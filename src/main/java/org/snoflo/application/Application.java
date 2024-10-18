package org.snoflo.application;

import org.snoflo.commander.EntryCommander;

public class Application {

    public void start() {

        EntryCommander entryCommander = new EntryCommanderFactory().creatEntryCommander();
        entryCommander.executeCommander();
    }

}
