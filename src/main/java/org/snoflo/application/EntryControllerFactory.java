package org.snoflo.application;

import java.util.Scanner;

import org.snoflo.controller.EntryController;
import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.function.RandomQuestion;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.FinderRepositoryImpl;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.repository.QuestionRepositoryImpl;
import org.snoflo.service.FinderService;
import org.snoflo.service.FinderServiceImpl;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.FinderView;
import org.snoflo.view.QuestionView;

import com.zaxxer.hikari.HikariDataSource;

public class EntryControllerFactory {

    private ResourceInitializer resourceInitializer;
    private ResourceHandler resourceHandler;

    public EntryControllerFactory() {
        this.resourceInitializer = new ResourceInitializer();
        this.resourceHandler = new ResourceHandler();
    }

    public EntryController createEntryController() {

        HikariDataSource hikariDataSource = resourceInitializer.getDataSource();
        Scanner scanner = resourceInitializer.getScanner();

        FinderController finderController = new ControllerFactory(hikariDataSource, scanner).createFinderController();
        QuestionController questionController = new ControllerFactory(hikariDataSource, scanner)
                .createQuestionController();

        EntryController entryController = new EntryController(resourceHandler, scanner, finderController,
                questionController);

        return entryController;
    }

    private class ControllerFactory {

        private HikariDataSource hikariDataSource;
        private Scanner scanner;

        public ControllerFactory(HikariDataSource hikariDataSource, Scanner scanner) {
            this.hikariDataSource = hikariDataSource;
            this.scanner = scanner;
        }

        public FinderController createFinderController() {

            CsvFileParser csvFileParser = new CsvFileParser();
            CsvFileFinder csvFileFinder = new CsvFileFinder();

            FinderRepository finderRepository = new FinderRepositoryImpl(hikariDataSource);
            FinderService finderService = new FinderServiceImpl(csvFileParser, finderRepository);
            FinderView finderView = new FinderView();
            FinderController finderController = new FinderController(scanner, csvFileFinder, finderService, finderView);

            return finderController;
        }

        public QuestionController createQuestionController() {

            RandomQuestion randomQuestion = new RandomQuestion();
            QuestionRepository questionRepository = new QuestionRepositoryImpl(hikariDataSource);
            QuestionService questionService = new QuestionServiceImpl(questionRepository);
            QuestionView questionView = new QuestionView();
            QuestionController questionController = new QuestionController(scanner, randomQuestion, questionService,
                    questionView);

            return questionController;
        }
    }
}
