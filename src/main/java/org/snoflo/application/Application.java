package org.snoflo.application;

import java.io.IOException;
import java.util.Scanner;

// import org.snoflo.controller.ControllerContext;
// import org.snoflo.controller.ControllerStrategy;
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

public class Application {

    // app
    private H2WebConsole h2Console = new H2WebConsole();

    // common
    private Scanner scanner = new Scanner(System.in);
    private HikariDataSource dataSource = AppDataSource.getInstance();
    
    // Finder finder = new Finder.Builder();
    
    // Finder
    private CsvFileParser csvFileParser = new CsvFileParser();
    private CsvFileFinder csvFileFinder = new CsvFileFinder();
    
    private FinderRepository finderRepository = new FinderRepositoryImpl(dataSource);
    private FinderService finderService = new FinderServiceImpl(finderRepository);
    private FinderView finderView = new FinderView();
    
    private FinderController finderController = new FinderController(scanner, csvFileParser, csvFileFinder,
            finderService,
            finderView);
    // Question
    private RandomQuestion randomQuestion = new RandomQuestion();
    
    private QuestionRepository questionRepository = new QuestionRepositoryImpl(dataSource);
    private QuestionService questionService = new QuestionServiceImpl(questionRepository);
    private QuestionView questionView = new QuestionView();
    private QuestionController questionController = new QuestionController(scanner, randomQuestion, questionService,
            questionView);
    // --------------------
    // FinderService

    // QuestionService

    // --------------------
    // view
    private MainView mainView = new MainView();
    
    private StartController startController = new StartController(scanner, mainView, finderController,
            questionController);
    // 시작
    // ----------------------------------------

    private ApplicationContext context = new ApplicationContext();

    public void start() {

        context.setConsole(h2Console);
        context.setScanner(scanner);
        context.setStrategy(startController);

        context.connectH2WebConsole();

        context.startController();
        

        dataSource.close();
        scanner.close();
        context.stopH2WebConsole();
        System.exit(0);
    }

}
