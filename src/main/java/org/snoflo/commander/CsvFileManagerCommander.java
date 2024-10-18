package org.snoflo.commander;

import java.nio.file.Path;

import org.snoflo.controller.CsvFileFinderController;
import org.snoflo.controller.CsvFileRegisterController;

public class CsvFileManagerCommander implements AppCommander {
    
    private CsvFileFinderController finderController;
    private CsvFileRegisterController registerController;

    public CsvFileManagerCommander(CsvFileFinderController finderController, CsvFileRegisterController registerController) {
        this.finderController = finderController;
        this.registerController = registerController;
    }

    @Override
    public void executeCommander() {
        Path selectedCsvFile = this.finderController.executeFinderController();
        this.registerController.executeRegisterController(selectedCsvFile);
        return;
    }
}
