package org.snoflo.controller;

import java.util.List;

import org.snoflo.domain.Question;
import org.snoflo.service.QuestionService;
import org.snoflo.view.MainView;
import org.snoflo.view.QuestionView;

public class QuestionController extends AppController {

    private QuestionService appService;
    private QuestionView questionView;
    private MainView mainView;

    public QuestionController(QuestionService appService, QuestionView questionView, MainView mainView) {
        this.appService = appService;
        this.questionView = questionView;
        this.mainView = mainView;
        executeMainMenu();
    }

    private void executeMainMenu() {
        mainView.showPromptMainMenu();
        mainView.showSelectMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1 -> executeFindAll();
            case 2 -> executeFindById();
            default -> executeMainMenu();
        }
    }

    private void executeFindAll() {
        List<Question> list = appService.findAll();
        questionView.showResultFindAll(list);
    }

    private void executeFindById() {
        questionView.showPromptFindById();
        int id = scanner.nextInt();
        scanner.nextLine();
        Question conceptById = appService.findConceptById(id);
        questionView.showResultFindById(conceptById);
    }

}
