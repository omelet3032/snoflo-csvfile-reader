package org.snoflo.application;

import java.io.IOException;
import java.sql.SQLException;

import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.domain.Question;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileReader;
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

    public void start() throws IOException {

        Question question = new Question();
        // CsvFileConverter dataConverter = new CsvFileConverter();
        CsvFileReader csvFileReader = new CsvFileReader(question);
        CsvFileFinder csvFileFinder = new CsvFileFinder();

        HikariConfig config = new HikariConfig("/application-hsqldb.properties");
        HikariDataSource dataSource = new HikariDataSource(config);
    
    
        // --------------------
        // FinderService
        FinderRepository finderRepository = new FinderRepository(dataSource);
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
        new FinderController(csvFileReader, csvFileFinder, finderService, finderView).start();
        new QuestionController(questionService, questionView, mainView).start();

    }

}
