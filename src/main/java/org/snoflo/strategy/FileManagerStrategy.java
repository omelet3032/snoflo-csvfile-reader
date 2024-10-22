package org.snoflo.strategy;

import java.nio.file.Path;

import org.snoflo.controller.FileFinderController;
import org.snoflo.controller.FileRegisterController;

public class FileManagerStrategy implements AppStrategy {
    
    private FileFinderController finderController;
    private FileRegisterController registerController;

    public FileManagerStrategy(FileFinderController finderController, FileRegisterController registerController) {
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
