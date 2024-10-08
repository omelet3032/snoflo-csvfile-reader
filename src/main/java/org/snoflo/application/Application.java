package org.snoflo.application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.Server;
import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.controller.StartController;
import org.snoflo.domain.Question;
import org.snoflo.function.AppDataSource;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.function.TableManager;
import org.snoflo.repository.FinderRepositoryImpl;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.service.FinderService;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.FinderView;
import org.snoflo.view.MainView;
import org.snoflo.view.QuestionView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Application {

    private Server webServer = new Server();

    public void start() throws IOException, IllegalArgumentException, IllegalAccessException {

        CsvFileParser csvFileReader = new CsvFileParser();
        CsvFileFinder csvFileFinder = new CsvFileFinder();

        HikariDataSource dataSource = AppDataSource.getInstance();

        // --------------------
        // FinderService
        FinderRepository finderRepository = new FinderRepositoryImpl(dataSource);
        FinderService finderService = new FinderService(finderRepository);

        // QuestionService
        QuestionRepository questionRepository = new QuestionRepository(dataSource);
        QuestionService questionService = new QuestionServiceImpl(questionRepository);

        // --------------------
        // view
        FinderView finderView = new FinderView();
        MainView mainView = new MainView();
        QuestionView questionView = new QuestionView();

        // 시작
        FinderController finderController = new FinderController(csvFileReader, csvFileFinder, finderService,
                finderView);
        QuestionController questionController = new QuestionController(questionService, questionView);
        StartController startController = new StartController(mainView, finderController, questionController);

        startController.start();

    }

  

}
