package org.snoflo.application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.snoflo.controller.AppController;
import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.db.CsvFileReader;
import org.snoflo.dbconnection.MySqlConfig;
import org.snoflo.dbconnection.MysqlConnection;
import org.snoflo.db.CsvFileConverter;
import org.snoflo.db.CsvFileManager;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.service.FinderService;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.FinderView;
import org.snoflo.view.MainView;
import org.snoflo.view.QuestionView;

public class Application {

	public void start() throws IOException {

        MysqlConnection connection = MysqlConnection.getInstance();
        connection.connect();

        // try (Statement stmt = connection.getConnection().createStatement()) {
        //     String query = "select version()";
        //     ResultSet rs = stmt.executeQuery(query);
        //     while (rs.next()) {
                
        //         String version = rs.getString("version()");
        //         System.out.println(version);
        //     }

        // } catch (SQLException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        // CsvFileConverter dataConverter = new CsvFileConverter();
        CsvFileManager csvFileManager = new CsvFileManager(); 
        // --------------------
        // FinderService
        FinderRepository finderRepository = new FinderRepository(csvFileManager);
        FinderService finderService = new FinderService(finderRepository);

        // QuestionService
        QuestionRepository questionRepository = new QuestionRepository(csvFileManager);
        QuestionService questionService = new QuestionServiceImpl(questionRepository);
        
        // --------------------
        // view
        FinderView finderView = new FinderView();
        MainView mainView = new MainView();
        QuestionView questionView = new QuestionView();
        
        // 시작
        new FinderController(finderService ,finderView).start();
        new QuestionController(questionService, questionView, mainView).start();

    }

}
