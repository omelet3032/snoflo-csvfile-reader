package org.snoflo.controller;

import java.nio.file.Path;

public class CustomHandler {
    
    private CsvFileFinderController finderController;
    private CsvFileRegisterController registerController;

    public CustomHandler(CsvFileFinderController finderController, CsvFileRegisterController registerController) {
        this.finderController = finderController;
        this.registerController = registerController;
    }

    public void executeHandler() {
        Path selectedCsvFile = this.finderController.executeFinderController();
        this.registerController.executeRegisterController(selectedCsvFile);
        return;
    }
}
