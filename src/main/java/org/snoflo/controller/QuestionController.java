package org.snoflo.controller;

import java.util.List;

import org.snoflo.domain.Question;
import org.snoflo.service.QuestionService;
import org.snoflo.view.AppView;

public class QuestionController extends AppController {

    private QuestionService appService;

    public QuestionController(QuestionService appService, AppView view) {
        super(view);
        this.appService = appService;
    }

    public void executeMainMenu() {
        view.showPromptMainMenu();
        view.showSelectMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1 -> executeFindAll();
            case 2 -> executeFindById();
            default -> executeMainMenu();
        }
    }
    
    private void executeFindAll() {
        List<Question> list = appService.findAll();
        view.showResultFindAll(list);
    }

    private void executeFindById() {
        view.showPromptFindById();
        int id = scanner.nextInt();
        scanner.nextLine();
        Question conceptById = appService.findConceptById(id);
        view.showResultFindById(conceptById);
    }




}
