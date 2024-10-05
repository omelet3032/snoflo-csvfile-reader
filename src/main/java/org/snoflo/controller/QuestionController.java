package org.snoflo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.snoflo.domain.Question;
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
            case 1 -> executeFindAll();
            case 2 -> executeFindById();
            case 3 -> executeRandomQuestion();
            default -> start();
        }
    }

    private void executeRandomQuestion() throws IllegalArgumentException, IllegalAccessException {
        /*
         * QuestionView에 퀴즈 시작합니다 화면 출력
         * findbyALl로 db에서 list 가져오기
         * 
         */

        questionView.showPromptRandomQuestion();
        scanner.nextLine();

        List<Question> list = quetionsService.findAllQuestion();
        RandomQuestion randomQuestion = new RandomQuestion();

        while (!list.isEmpty()) {
                // System.out.println("순회 시작");
                // Map<String, String> map = randomQuestion.getRandomQuestion(list);
                Map<Question, List<String>> map = randomQuestion.getRandomQuestion(list);

                // for (Map.Entry<String, String> entry : map.entrySet()) {
                // for (Map.Entry<Question, List<String>> entry : map.entrySet()) {
                //     questionView.showResultQuestionField(entry.getKey());
                //     scanner.nextLine();
                //     questionView.showResultAnswerField(entry.getValue());
                //     scanner.nextLine();
                // }

                Set<Question> key = map.keySet();
                Question question = key.iterator().next();

                for (List<String> value : map.values()) {
                    questionView.showResultQuestionField(value.getFirst());
                    scanner.nextLine();
                    questionView.showResultAnswerField(value.getLast());
                    scanner.nextLine();
                }

                list.remove(question);

            // System.out.println("순회 끝");
            // System.out.println("list.size() : " + list.size());

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
