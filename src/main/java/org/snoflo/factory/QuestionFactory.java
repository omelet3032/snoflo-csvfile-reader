package org.snoflo.factory;

import java.util.Scanner;

import org.snoflo.builder.AppControllerBuilder;
import org.snoflo.builder.QuestionControllerBuilder;
import org.snoflo.controller.QuestionController;
import org.snoflo.function.AppDataSource;
import org.snoflo.function.RandomQuestion;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.repository.QuestionRepositoryImpl;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.QuestionView;

import com.zaxxer.hikari.HikariDataSource;

public class QuestionFactory implements AppFactory {

	private HikariDataSource dataSource;
    private Scanner scanner;
    private RandomQuestion randomQuestion;
    
    private QuestionRepository questionRepository;
    private QuestionService questionService;
    private QuestionView questionView;
    private QuestionController questionController;

    @Override
    public QuestionFactory createComponent() {
        this.dataSource = AppDataSource.getInstance();
        this.scanner = new Scanner(System.in);
        this.randomQuestion = new RandomQuestion();

        this.questionRepository = new QuestionRepositoryImpl(dataSource);
        this.questionService = new QuestionServiceImpl(questionRepository);
        this.questionView = new QuestionView();
        this.questionController = new QuestionController(scanner, randomQuestion, questionService, questionView);

        return this;
    }

    @Override
	public QuestionControllerBuilder createBuilder() {

        QuestionControllerBuilder questionControllerBuilder = new QuestionControllerBuilder.Builder()
        .dataSource(dataSource)
        .scanner(scanner)
        .randomQuestion(randomQuestion)
        .repository(questionRepository)
        .service(questionService)
        .view(questionView)
        .controller(questionController)
        .build();

        return questionControllerBuilder;
	}
}
