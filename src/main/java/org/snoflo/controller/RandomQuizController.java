package org.snoflo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.snoflo.domain.Question;
import org.snoflo.domain.Row;
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

    private void executeRandomQuiz(List<Row> cachedList) {
        questionView.showPromptRandomQuestion();
        scanner.nextLine();

        List<Question> questionList = randomQuestionGenerator.getRandomQuestion(cachedList);

        // Iterator<Question> iterator = questionList.iterator();

        // while (iterator.hasNext()) {

        // Question question = iterator.next();
        // questionView.showResultQuestionField(question.getQuestion());
        // scanner.nextLine();
        // questionView.showResultAnswerField(question.getAnswer());
        // scanner.nextLine();

        // iterator.remove();
        // }
        randomQuiz.playRandomQuiz(questionList, questionView, scanner);
    }

}
