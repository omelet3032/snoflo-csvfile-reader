package org.snoflo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.snoflo.domain.Question;
import org.snoflo.dto.RandomQuestionDto;
import org.snoflo.function.RandomQuestion;
import org.snoflo.service.QuestionService;
import org.snoflo.view.QuestionView;

public class QuestionController implements AppController {

    private QuestionService quetionsService;
    private QuestionView questionView;
    private RandomQuestion randomQuestion;

    private Scanner scanner;

    public QuestionController(Scanner scanner, RandomQuestion randomQuestion, QuestionService questionService,
            QuestionView questionView) {
        this.randomQuestion = randomQuestion;
        this.scanner = scanner;
        this.quetionsService = questionService;
        this.questionView = questionView;
    }

    @Override
    public void start() {

        List<String> tableList = quetionsService.findQuestionTable();

        questionView.showSelectRegisterdFileMenu(tableList);
        int number = scanner.nextInt();
        scanner.nextLine();

        String selectedTable = tableList.get(--number);
        List<Question> list = quetionsService.findAllQuestion(selectedTable);

        List<Question> cachedList = new ArrayList<>(list);

        executeRandomQuiz(cachedList);
        while (true) {

            questionView.showSelectAskPlay();
            String answer = scanner.nextLine();

            if (answer.equals("Y")) {
                cachedList.addAll(list);
                executeRandomQuiz(cachedList);
                continue;
            } else if (answer.equals("n")) {
                questionView.showPromptReturnMainMenu();
                return;
            } else {
                questionView.showPromptYorN();
            }
        }

    }

    private void executeRandomQuiz(List<Question> cachedList) {
        questionView.showPromptRandomQuestion();
        scanner.nextLine();
        playRandomQuiz(cachedList);
    }

    private List<Question> playRandomQuiz(List<Question> list) {

        while (!list.isEmpty()) {
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

        return list;
    }
}
