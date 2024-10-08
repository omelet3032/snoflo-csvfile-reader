package org.snoflo.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.snoflo.domain.Question;
import org.snoflo.dto.RandomQuestionDto;
import org.snoflo.function.RandomQuestion;
import org.snoflo.service.QuestionService;
import org.snoflo.view.QuestionView;

public class QuestionController extends AppController implements CommonControllerInterface {

    private QuestionService quetionsService;
    private QuestionView questionView;
    private RandomQuestion randomQuestion;

    public QuestionController(RandomQuestion randomQuestion, QuestionService questionService, QuestionView questionView) {
        this.randomQuestion = randomQuestion;
        this.quetionsService = questionService;
        this.questionView = questionView;
    }

    public void executeRandomQuestion() throws IllegalArgumentException, IllegalAccessException {

        List<String> tableList = quetionsService.findQuestionTable();

        questionView.showSelectRegisterdFileMenu(tableList);
        int number = scanner.nextInt();
        scanner.nextLine();

        String selectedTable = tableList.get(--number);

        

        questionView.showPromptRandomQuestion();
        scanner.nextLine();

        List<Question> list = quetionsService.findAllQuestion(selectedTable);

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
