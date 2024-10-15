package org.snoflo.controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.snoflo.application.ApplicationStrategy;
import org.snoflo.domain.Question;
import org.snoflo.dto.RandomQuestionDto;
import org.snoflo.function.RandomQuestion;
import org.snoflo.service.QuestionService;
import org.snoflo.view.QuestionView;

public class QuestionController implements ApplicationStrategy, AppController { 

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

        questionView.showPromptRandomQuestion();
        scanner.nextLine();

        List<Question> list = quetionsService.findAllQuestion(selectedTable);

        playRandomQuiz(list);

        questionView.showPromptAskPlay();
        String answer = scanner.nextLine();
        if (answer.equals("Y")) {
            start();
        } else if (answer.equals("n")) {
            return;
        }

    }

    private void playRandomQuiz(List<Question> list) {
        
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
    }
}
