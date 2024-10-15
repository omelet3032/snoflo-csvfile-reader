package org.snoflo.factory;

import java.util.Scanner;

import org.snoflo.builder.AppSystemBuilder;
import org.snoflo.builder.QuestionSystemBuilder;
import org.snoflo.controller.QuestionController;
import org.snoflo.entry.ResourceManager;
import org.snoflo.function.AppDataSource;
import org.snoflo.function.RandomQuestion;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.repository.QuestionRepositoryImpl;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.system.AppSystem;
import org.snoflo.system.QuestionSystem;
import org.snoflo.view.QuestionView;

import com.zaxxer.hikari.HikariDataSource;

public class QuestionFactory extends AppFactory {

    private HikariDataSource hikariDataSource;
    private Scanner scanner;

    private RandomQuestion randomQuestion;

    private QuestionRepository questionRepository;
    private QuestionService questionService;
    private QuestionView questionView;
    private QuestionController questionController;

    @Override
    protected AppSystem createProduct(ResourceManager resourceManager) {
        initialize(resourceManager);

        QuestionSystemBuilder questionSystemBuilder = new QuestionSystemBuilder.Builder()
                .dataSource(hikariDataSource)
                .input(scanner)
                .functionWithController(randomQuestion)
                .repository(questionRepository)
                .service(questionService)
                .view(questionView)
                .controller(questionController)
                .build();

        return new QuestionSystem(questionSystemBuilder);
    }


    @Override
    protected void initialize(ResourceManager resourceManager) {
        this.hikariDataSource = resourceManager.getDataSource();
        this.scanner = resourceManager.getScanner();

        this.randomQuestion = new RandomQuestion();

        this.questionRepository = new QuestionRepositoryImpl(hikariDataSource);
        this.questionService = new QuestionServiceImpl(questionRepository);
        this.questionView = new QuestionView();
        this.questionController = new QuestionController(scanner, randomQuestion, questionService, questionView);
    }

}
