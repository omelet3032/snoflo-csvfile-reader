package org.snoflo.application;

import java.io.IOException;
import java.util.Scanner;

import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.controller.StartController;
import org.snoflo.function.AppDataSource;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.function.H2WebConsole;
import org.snoflo.function.RandomQuestion;
import org.snoflo.repository.FinderRepositoryImpl;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.repository.QuestionRepositoryImpl;
import org.snoflo.service.FinderServiceImpl;
import org.snoflo.service.FinderService;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.FinderView;
import org.snoflo.view.MainView;
import org.snoflo.view.QuestionView;

import com.zaxxer.hikari.HikariDataSource;

public class ApplicationBU {


    public void start() throws IOException, IllegalArgumentException, IllegalAccessException {

        H2WebConsole console = new H2WebConsole();

        console.connect();

        Scanner scanner = new Scanner(System.in);

        CsvFileParser csvFileReader = new CsvFileParser();
        CsvFileFinder csvFileFinder = new CsvFileFinder();

        RandomQuestion randomQuestion = new RandomQuestion();

        HikariDataSource dataSource = AppDataSource.getInstance();

        // --------------------
        // FinderService
        FinderRepository finderRepository = new FinderRepositoryImpl(dataSource);
        FinderService finderService = new FinderServiceImpl(finderRepository);

        // QuestionService
        QuestionRepository questionRepository = new QuestionRepositoryImpl(dataSource);
        QuestionService questionService = new QuestionServiceImpl(questionRepository);

        // --------------------
        // view
        FinderView finderView = new FinderView();
        QuestionView questionView = new QuestionView();
        MainView mainView = new MainView();

        // 시작
        FinderController finderController = new FinderController(scanner, csvFileReader, csvFileFinder, finderService,
                finderView);
        QuestionController questionController = new QuestionController(scanner, randomQuestion, questionService, questionView);
        StartController startController = new StartController(scanner, mainView, finderController, questionController);

        startController.start();
        
        scanner.close();
        console.stop();

        startController.exitApp();
    }

}
