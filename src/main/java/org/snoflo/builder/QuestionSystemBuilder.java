package org.snoflo.builder;

import java.util.Scanner;

import org.snoflo.controller.AppController;
import org.snoflo.controller.QuestionController;
import org.snoflo.function.AppDataSource;
import org.snoflo.function.RandomQuestion;
import org.snoflo.view.AppView;
import org.snoflo.view.QuestionView;
import org.snoflo.repository.AppRepository;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.repository.QuestionRepositoryImpl;
import org.snoflo.service.AppService;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import com.zaxxer.hikari.HikariDataSource;

public class QuestionSystemBuilder implements AppSystemBuilder {

    private HikariDataSource dataSource;
    private Scanner scanner;

    private RandomQuestion randomQuestion;

    private QuestionRepository questionRepository;
    private QuestionService questionService;
    private QuestionView questionView;
    private QuestionController questionController;

    private QuestionSystemBuilder(Builder builder) {
        this.dataSource = builder.dataSource;
        this.scanner = builder.scanner;
        this.randomQuestion = builder.randomQuestion;
        this.questionRepository = builder.questionRepository;
        this.questionService = builder.questionService;
        this.questionView = builder.questionView;
        this.questionController = builder.questionController;
    }

    @Override
    public QuestionController getController() {
        return this.questionController;
    }

    public static class Builder {

        private Scanner scanner;
        private HikariDataSource dataSource;

        private RandomQuestion randomQuestion;

        private QuestionRepository questionRepository;
        private QuestionService questionService;
        private QuestionView questionView;
        private QuestionController questionController;

        public Builder dataSource(HikariDataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public Builder input(Scanner scanner) {
            this.scanner = scanner;
            return this;
        }

        public Builder functionWithController(RandomQuestion randomQuestion) {
            this.randomQuestion = randomQuestion;
            return this;
        }

        public Builder repository(QuestionRepository questionRepository) {
            this.questionRepository = questionRepository;
            return this;
        }

        public Builder service(QuestionService questionService) {
            this.questionService = questionService;
            return this;
        }

        public Builder view(QuestionView questionView) {
            this.questionView = questionView;
            return this;
        }

        public Builder controller(QuestionController questionController) {
            this.questionController = questionController;
            return this;
        }

        public QuestionSystemBuilder build() {
            return new QuestionSystemBuilder(this);
        }

    }

}
