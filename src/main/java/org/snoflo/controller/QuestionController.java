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

public class QuestionController extends AppController implements MainController {

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
            // case 1 -> executeRandomQuestion();
            // case 2 -> executeFindAll();
            case 3 -> exitApp();
            default -> start();
        }
    }

    // private void executeRandomQuestion() throws IllegalArgumentException, IllegalAccessException {
    public void executeRandomQuestion(String selectedFile) throws IllegalArgumentException, IllegalAccessException {

        /* 
         * 여기서 테이블 확인
         */

        questionView.showPromptRandomQuestion();
        scanner.nextLine();

        // List<Question> list = quetionsService.findAllQuestion();
        List<Question> list = quetionsService.findAllQuestion(selectedFile);
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

        while(true) {
            questionView.showPromptAskExit();
            String input = scanner.nextLine();
            
            if (input.equals("Y")) {
                exitApp();
            } else if (input.equalsIgnoreCase("n")) {
                start();
            } 
            
        }

    }

    // private void executeFindAll() {
    //     List<Question> list = quetionsService.findAllQuestion();
    //     questionView.showResultFindAll(list);
    // }

    private void executeFindById() {
        questionView.showPromptFindById();
        int id = scanner.nextInt();
        scanner.nextLine();
        Question conceptById = quetionsService.findConceptById(id);
        questionView.showResultFindById(conceptById);
    }

}
