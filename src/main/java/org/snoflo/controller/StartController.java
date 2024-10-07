package org.snoflo.controller;

import java.util.List;

import org.snoflo.function.DbTableManager;
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
        mainView.showSelectStartMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            // case 1 -> questionController.start();
            case 1 -> start1();
            case 2 -> finderController.start();
            case 3 -> exitApp();
        }
    }

    public void start1() throws IllegalArgumentException, IllegalAccessException {
        mainView.showSelectRegisterdFileMenu(checkTable());
        
        if (checkTable().isEmpty()) {
            System.out.println("등록된 파일이 없습니다.");
            start();
        }
        
        int number = scanner.nextInt();
        scanner.nextLine();

        String selectedTable = checkTable().get(number-1);
        

        questionController.executeRandomQuestion(selectedTable.toLowerCase());

        // questionController.start();
    }

    public List<String> checkTable() {
        HikariConfig config = new HikariConfig("/application-h2.properties");
        HikariDataSource dataSource = new HikariDataSource(config);

        DbTableManager tableCreator = new DbTableManager(dataSource);
        return tableCreator.getTable();

    }
}
