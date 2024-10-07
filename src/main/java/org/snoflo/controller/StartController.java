package org.snoflo.controller;

import java.util.List;

import org.snoflo.function.TableManager;
import org.snoflo.view.MainView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class StartController extends AppController implements CommonControllerInterface {

    private MainView mainView;
    private FinderController finderController;
    private QuestionController questionController;

    public StartController(MainView mainView, FinderController finderController,
            QuestionController questionController) {
        this.mainView = mainView;
        this.finderController = finderController;
        this.questionController = questionController;
    }

    public void start() throws IllegalArgumentException, IllegalAccessException {
       
        while(true) {

            mainView.showSelectStartMenu();
            int number = Integer.parseInt(scanner.nextLine());
    
            switch (number) {
                // case 1 -> questionController.start();
                case 1 -> startQuestionController();
                case 2 -> startFinderController();
                case 3 -> exitApp();
            }
        }
    }


    public void startFinderController() {
        finderController.start();
    }

    public void startQuestionController() throws IllegalArgumentException, IllegalAccessException {
        
        // if (checkTable().isEmpty()) {
        //     System.out.println("등록된 파일이 없습니다.");
        //     start();
        // }
        
        questionController.executeRandomQuestion();

    }

    // public List<String> checkTable() {
    //     HikariConfig config = new HikariConfig("/application-h2.properties");
    //     HikariDataSource dataSource = new HikariDataSource(config);

    //     TableManager tableCreator = new TableManager(dataSource);
    //     return tableCreator.getTable();

    // }
}
