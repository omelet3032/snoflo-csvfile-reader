package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.dto.CsvFileDto;
import org.snoflo.model.Question;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.AppView;

public class QuestionController extends AppController {

    // public QuestionController (AppService<Question> appService) {
    // this.appService = appService;
    // this.view = new AppView();
    // this.scanner = new Scanner(System.in);
    // executeMainMenu();
    // }

    public QuestionController() {
        this.view = new AppView();
        this.appService = new QuestionServiceImpl(null);
        this.scanner = new Scanner(System.in);
        executeMainMenu();
    }

    private void executeMainMenu() {
        view.showPromptMainMenu();
        view.showSelectMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1 -> executeFindById();
            default -> executeMainMenu();
        }

    }

    private void executeFindById() {
        view.showPromptFindById();
        int id = scanner.nextInt();
        scanner.nextLine();
        Question conceptById = appService.findConceptById(id);
        view.showResultFindById(conceptById);
    }

}
