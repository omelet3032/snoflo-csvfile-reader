package org.snoflo.strategy;

import java.nio.file.Path;

import org.snoflo.controller.CsvFileFinderController;
import org.snoflo.controller.CsvFileRegisterController;

public class CsvFileManagerStrategy implements AppStrategy {
    
    private CsvFileFinderController finderController;
    private CsvFileRegisterController registerController;

    public CsvFileManagerStrategy(CsvFileFinderController finderController, CsvFileRegisterController registerController) {
        this.finderController = finderController;
        this.registerController = registerController;
    }

    @Override
    public void runStrategy() {
        Path selectedCsvFile = this.finderController.executeFinderController();
        this.registerController.executeRegisterController(selectedCsvFile);
        return;
    }
}
