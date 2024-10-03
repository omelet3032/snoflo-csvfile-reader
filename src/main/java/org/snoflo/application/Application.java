package org.snoflo.application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.Server;
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


        connect();

        Question question = new Question();
        // CsvFileReader csvFileReader = new CsvFileReader(question);
        List<Question> list = new ArrayList<>();
        // CsvFileReader csvFileReader = new CsvFileReader(list);
        CsvFileReader csvFileReader = new CsvFileReader();
        CsvFileFinder csvFileFinder = new CsvFileFinder();

        HikariConfig config = new HikariConfig("/application-h2.properties");
        // HikariConfig config = new HikariConfig("db/h2/application-h2.properties");
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

    private void connect() {
        try {
			Server webServer = Server.createWebServer("-webAllowOthers", "-webPort", "8082").start();
            Server tcpServer = Server.createTcpServer("-tcpAllowOthers", "-tcpPort", "9092").start();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
