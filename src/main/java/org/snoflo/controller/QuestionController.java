package org.snoflo.controller;

import java.util.List;

import org.snoflo.domain.Question;
import org.snoflo.service.QuestionService;
import org.snoflo.view.MainView;
import org.snoflo.view.QuestionView;

public class QuestionController extends AppController {

    private QuestionService quetionsService;
    private QuestionView questionView;
    private MainView mainView;

    public QuestionController(QuestionService questionService, QuestionView questionView, MainView mainView) {
        this.quetionsService = questionService;
        this.questionView = questionView;
        this.mainView = mainView;
    }

    public void start() {
        mainView.showPromptMainMenu();
        mainView.showSelectMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1 -> executeFindAll();
            case 2 -> executeFindById();
            default -> start();
        }
    }

    private void executeFindAll() {
        List<Question> list = quetionsService.findAllQuestion();
        questionView.showResultFindAll(list);
    }

    private void executeFindById() {
        questionView.showPromptFindById();
        int id = scanner.nextInt();
        scanner.nextLine();
        Question conceptById = quetionsService.findConceptById(id);
        questionView.showResultFindById(conceptById);
    }

}
