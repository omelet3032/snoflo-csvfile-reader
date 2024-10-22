package org.snoflo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.snoflo.domain.Row;
import org.snoflo.dto.QuestionDto;
import org.snoflo.dto.RandomQuestionDto;
import org.snoflo.function.RandomQuestionGenerator;
import org.snoflo.function.RandomQuiz;
import org.snoflo.service.RandomQuizService;
import org.snoflo.view.RandomQuizView;

public class RandomQuizController {

    private RandomQuizService quetionsService;
    private RandomQuizView questionView;

    private Scanner scanner;

    private RandomQuiz randomQuiz;
    private RandomQuestionGenerator randomQuestionGenerator;

    public RandomQuizController(Scanner scanner, RandomQuiz randomQuiz,
            RandomQuizService questionService,
            RandomQuizView questionView) {
        this.scanner = scanner;
        this.quetionsService = questionService;
        this.questionView = questionView;
        this.randomQuiz = randomQuiz;
        this.randomQuestionGenerator = new RandomQuestionGenerator();
    }

    public void start() {

        List<String> tableList = quetionsService.findQuestionTable();

        questionView.showSelectRegisterdFileMenu(tableList);
        int number = scanner.nextInt();
        scanner.nextLine();

        String selectedTable = tableList.get(--number);
        List<Row> list = quetionsService.findAllQuestion(selectedTable);

        List<Row> cachedList = new ArrayList<>(list);

        // List<QuestionDto> questionList = randomQuestionGenerator.getRandomQuestion2(cachedList);
        // randomQuiz.playRandomQuiz2(questionList, questionView, scanner);

        executeRandomQuiz2(cachedList);

        while (true) {

            questionView.showSelectAskPlay();
            questionView.showSelectYorN();
            String answer = scanner.nextLine();

            if (answer.equals("Y")) {
                cachedList.addAll(list);
                executeRandomQuiz2(cachedList);
                continue;
            } else if (answer.equals("n")) {
                questionView.showPromptReturnMainMenu();
                return;
            } else {
                questionView.showSelectYorN();
            }
        }

    }

    private void executeRandomQuiz(List<Row> cachedList) {
        questionView.showPromptRandomQuestion();
        scanner.nextLine();
        // randomQuestionGenerator.getRandomQuestion(cachedList);
        randomQuiz.playRandomQuiz(cachedList, questionView, scanner);
    }

    private void executeRandomQuiz2(List<Row> cachedList) {
        List<QuestionDto> questionList = randomQuestionGenerator.getRandomQuestion2(cachedList);
        randomQuiz.playRandomQuiz2(questionList, questionView, scanner);
    }

}
