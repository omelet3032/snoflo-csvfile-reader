package org.snoflo.factory;

import java.util.Scanner;

import org.snoflo.builder.AppSystemBuilder;
import org.snoflo.builder.QuestionSystemBuilder;
import org.snoflo.controller.QuestionController;
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

public class QuestionFactory implements AppFactory {

    @Override
    public AppSystem createSystem() {
        HikariDataSource hikariDataSource = AppDataSource.getInstance();
        Scanner scanner = new Scanner(System.in);

        QuestionRepository questionRepository = new QuestionRepositoryImpl(hikariDataSource);
        QuestionService questionService = new QuestionServiceImpl(questionRepository);
        QuestionView questionView = new QuestionView();
        QuestionController questionController = new QuestionController(scanner, questionService, questionView);

        QuestionSystemBuilder questionSystemBuilder = new QuestionSystemBuilder.Builder()
                .dataSource(hikariDataSource)
                .scanner(scanner)
                .repository(questionRepository)
                .service(questionService)
                .view(questionView)
                .controller(questionController)
                .build();

        QuestionSystem finderSystem = new QuestionSystem(questionSystemBuilder);
        
        return finderSystem;
    }

}
