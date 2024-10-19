package org.snoflo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.snoflo.domain.CsvFileRow;
import org.snoflo.dto.RandomQuestionDto;
import org.snoflo.function.RandomQuestion;
import org.snoflo.function.RandomQuiz;
import org.snoflo.service.RandomQuizService;
import org.snoflo.view.RandomQuizView;

public class RandomQuizController {

    private RandomQuizService quetionsService;
    private RandomQuizView questionView;

    private Scanner scanner;

    private RandomQuiz randomQuiz;

    public RandomQuizController(Scanner scanner, RandomQuiz randomQuiz,
            RandomQuizService questionService,
            RandomQuizView questionView) {
        this.scanner = scanner;
        this.quetionsService = questionService;
        this.questionView = questionView;
        this.randomQuiz = randomQuiz;
    }

    public void start() {

        List<String> tableList = quetionsService.findQuestionTable();

        questionView.showSelectRegisterdFileMenu(tableList);
        int number = scanner.nextInt();
        scanner.nextLine();

        String selectedTable = tableList.get(--number);
        List<CsvFileRow> list = quetionsService.findAllQuestion(selectedTable);

        List<CsvFileRow> cachedList = new ArrayList<>(list);

        executeRandomQuiz(cachedList);

        while (true) {

            questionView.showSelectAskPlay();
            questionView.showSelectYorN();
            String answer = scanner.nextLine();

            if (answer.equals("Y")) {
                cachedList.addAll(list);
                executeRandomQuiz(cachedList);
                continue;
            } else if (answer.equals("n")) {
                questionView.showPromptReturnMainMenu();
                return;
            } else {
                questionView.showSelectYorN();
            }
        }

    }

    private void executeRandomQuiz(List<CsvFileRow> cachedList) {
        questionView.showPromptRandomQuestion();
        scanner.nextLine();
        randomQuiz.playRandomQuiz(cachedList, questionView, scanner);
    }

}
