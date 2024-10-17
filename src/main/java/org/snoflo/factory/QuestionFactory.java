package org.snoflo.factory;

import java.util.Scanner;

import org.snoflo.application.ResourceManager;
import org.snoflo.controller.AppController;
import org.snoflo.controller.QuestionController;
import org.snoflo.function.AppDataSource;
import org.snoflo.function.RandomQuestion;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.repository.QuestionRepositoryImpl;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.QuestionView;

import com.zaxxer.hikari.HikariDataSource;

public class QuestionFactory extends AppFactory {

    @Override
    public AppController createProduct(ResourceManager resourceManager) {
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
