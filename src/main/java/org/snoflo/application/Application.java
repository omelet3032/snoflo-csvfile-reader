package org.snoflo.application;

import java.io.IOException;

import org.snoflo.controller.AppController;
import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.dto.CsvFileDto;
import org.snoflo.repository.QuestionCsvFileReader;
import org.snoflo.repository.QuestionDataConverter;
import org.snoflo.service.CsvFilesFinderService;
import org.snoflo.service.QuestionServiceImpl;

public class Application {

    private AppController appController;
    private FinderController finderController;
    private QuestionController questionController;

    public Application(AppController appController) {
        this.appController = appController;
    }

    // public Application () {
    //     this.finderController = new FinderController(new CsvFilesFinderService());
    // }

    public Application () {
    }
    
    public void start() throws IOException {
        this.finderController = new FinderController(new CsvFilesFinderService());
        CsvFileDto csvFileDto = finderController.setFolderAndFile();
        // 이시점에서 dto가 결정됨
        this.questionController = new QuestionController(new QuestionServiceImpl(new QuestionDataConverter(new QuestionCsvFileReader(), csvFileDto)));
    }
  
}
