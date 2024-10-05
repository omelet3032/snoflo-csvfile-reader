package org.snoflo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.snoflo.domain.Question;
import org.snoflo.dto.RandomQuestionDto;
import org.snoflo.function.RandomQuestion;
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

    public void start() throws IllegalArgumentException, IllegalAccessException {
        mainView.showPromptMainMenu();
        mainView.showSelectMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1 -> executeRandomQuestion();
            case 2 -> executeFindAll();
            default -> start();
        }
    }

    private void executeRandomQuestion() throws IllegalArgumentException, IllegalAccessException {

        questionView.showPromptRandomQuestion();
        scanner.nextLine();

        List<Question> list = quetionsService.findAllQuestion();
        RandomQuestion randomQuestion = new RandomQuestion();

        while (!list.isEmpty()) {
            // Map<Question, List<String>> map = randomQuestion.getRandomQuestion(list);
            Map<Question, RandomQuestionDto> map = randomQuestion.getRandomQuestion(list);

            Set<Question> key = map.keySet();
            Question question = key.iterator().next();

            for (RandomQuestionDto dto : map.values()) {
                questionView.showResultQuestionField(dto.question());
                scanner.nextLine();
                questionView.showResultAnswerField(dto.answer());
                scanner.nextLine();
            }



            list.remove(question);

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
