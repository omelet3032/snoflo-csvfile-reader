package org.snoflo.application;

import java.util.Scanner;

import org.snoflo.controller.QuestionController;
import org.snoflo.function.RandomQuestion;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.repository.QuestionRepositoryImpl;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.QuestionView;

import com.zaxxer.hikari.HikariDataSource;

public class QuestionFactory2 {
    
    public QuestionController getQuestionController(ResourceManager resourceManager) {
        HikariDataSource hikariDataSource = resourceManager.getDataSource();
        Scanner scanner = resourceManager.getScanner();

        RandomQuestion randomQuestion = new RandomQuestion();
        QuestionRepository questionRepository = new QuestionRepositoryImpl(hikariDataSource);
        QuestionService questionService = new QuestionServiceImpl(questionRepository);
        QuestionView questionView = new QuestionView();
        QuestionController questionController = new QuestionController(scanner, randomQuestion, questionService,
                questionView);

        return questionController;
    }
}
