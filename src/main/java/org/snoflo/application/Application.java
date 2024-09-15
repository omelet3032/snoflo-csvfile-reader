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
import org.snoflo.view.AppView;
import org.snoflo.view.FinderView;
import org.snoflo.view.MainView;
import org.snoflo.view.QuestionView;
import org.snoflo.viewfactory.AppViewFactory;
import org.snoflo.viewfactory.FinderViewFactory;
import org.snoflo.viewfactory.MainViewFactory;
import org.snoflo.viewfactory.QuestionViewFactory;

public class Application {

    private FinderController finderController;
    private QuestionController questionController;


    public void start() throws IOException {
        
        this.finderController = new FinderController(new CsvFilesFinderService(), new FinderView());
        CsvFileDto csvFileDto = finderController.setFolderAndFile();
        this.questionController = new QuestionController(new QuestionServiceImpl(new QuestionDataConverter(new QuestionCsvFileReader(), csvFileDto)), new QuestionView(), new MainView());

    }
  
}
