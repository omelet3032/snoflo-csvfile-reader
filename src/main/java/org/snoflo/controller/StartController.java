package org.snoflo.controller;

import java.util.List;

import org.snoflo.db.TableCreator;
import org.snoflo.view.MainView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class StartController extends AppController implements MainController {

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

    /*
     * 로직
     * 시작하기 클릭
     * 1. checkTable()
     * 2. showSelectRegisterdFileMenu()
     * 3. questionController.start()
     * 
     */
    public void start1() throws IllegalArgumentException, IllegalAccessException {
        mainView.showSelectRegisterdFileMenu(checkTable());
        int number = scanner.nextInt();
        scanner.nextLine();

        String selectedTable = checkTable().get(number);
        
        questionController.executeRandomQuestion(selectedTable);

        // questionController.start();
    }

    public List<String> checkTable() {
        HikariConfig config = new HikariConfig("/application-h2.properties");
        HikariDataSource dataSource = new HikariDataSource(config);

        TableCreator tableCreator = new TableCreator(dataSource);
        return tableCreator.getTable();

    }
}
