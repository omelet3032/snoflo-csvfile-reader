package org.snoflo.application;

import java.io.IOException;

import org.snoflo.controller.AppController;
import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.repository.CsvFileReader;
import org.snoflo.repository.QuestionCsvFileReader;
import org.snoflo.repository.QuestionDataConverter;
import org.snoflo.service.FinderService;
import org.snoflo.service.FinderServiceImpl;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.FinderView;
import org.snoflo.view.MainView;
import org.snoflo.view.QuestionView;

public class Application {

	public void start() throws IOException {

        // FileFinder fileFinder = new FileFinder();
        
        CsvFileReader csvFileReader = new QuestionCsvFileReader();
        QuestionDataConverter dataConverter = new QuestionDataConverter(csvFileReader);
        
        // FinderService
        FinderService finderService = new FinderServiceImpl(dataConverter);
        
        // --------------------
        // QuestionService
        QuestionService questionService = new QuestionServiceImpl(dataConverter);
        
        // --------------------
        // view
        FinderView finderView = new FinderView();
        MainView mainView = new MainView();
        QuestionView questionView = new QuestionView();
        
        // 시작
        // new FinderController(fileFinder,finderService, finderView).sendSelectedFileToService();
        new FinderController(finderService, finderView).sendSelectedFileToService();
        new QuestionController(questionService, questionView, mainView).executeMainMenu();

    }

}
