package org.snoflo.application;

import java.util.Scanner;

import org.snoflo.controller.EntryController;
import org.snoflo.controller.CsvFileFinderController;
import org.snoflo.controller.CsvFileRegisterController;
import org.snoflo.controller.CustomHandler;
import org.snoflo.controller.QuestionController;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.function.RandomQuestion;
import org.snoflo.function.RandomQuiz;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.FinderRepositoryImpl;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.repository.QuestionRepositoryImpl;
import org.snoflo.service.FinderService;
import org.snoflo.service.FinderServiceImpl;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.CsvFileFinderView;
import org.snoflo.view.CsvFileRegisterView;
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

        CsvFileFinderController csvFileFinderController = new ControllerFactory(scanner).createCsvFileFinderController();
        CsvFileRegisterController csvFileRegisterController = new ControllerFactory(hikariDataSource, scanner)
                .createFinderController();
        QuestionController questionController = new ControllerFactory(hikariDataSource, scanner)
                .createQuestionController();

        CustomHandler handler = new CustomHandler(csvFileFinderController, csvFileRegisterController);

        // EntryController entryController = new EntryController(resourceHandler,
        // scanner, csvFileRegisterController,
        // questionController);

        EntryController entryController = new EntryController(resourceHandler, scanner, handler,
                questionController);

        return entryController;
    }

    private class ControllerFactory {

        private HikariDataSource hikariDataSource;
        private Scanner scanner;

        public ControllerFactory(Scanner scanner) {
            this.scanner = scanner;
        }

        public ControllerFactory(HikariDataSource hikariDataSource, Scanner scanner) {
            this.hikariDataSource = hikariDataSource;
            this.scanner = scanner;
        }

        public CsvFileRegisterController createFinderController() {

            CsvFileParser csvFileParser = new CsvFileParser();

            FinderRepository finderRepository = new FinderRepositoryImpl(hikariDataSource);
            FinderService finderService = new FinderServiceImpl(csvFileParser, finderRepository);
            CsvFileRegisterView registerView = new CsvFileRegisterView();
            CsvFileRegisterController finderController = new CsvFileRegisterController(scanner, finderService,
                    registerView);

            return finderController;
        }

        public QuestionController createQuestionController() {

            RandomQuiz randomQuiz = new RandomQuiz();
            RandomQuestion randomQuestion = new RandomQuestion();
            QuestionRepository questionRepository = new QuestionRepositoryImpl(hikariDataSource);
            QuestionService questionService = new QuestionServiceImpl(questionRepository);
            QuestionView questionView = new QuestionView();
            QuestionController questionController = new QuestionController(scanner, randomQuestion, randomQuiz,
                    questionService,
                    questionView);

            return questionController;
        }

        public CsvFileFinderController createCsvFileFinderController() {
            CsvFileFinder csvFileFinder = new CsvFileFinder();
            CsvFileFinderView csvFileFinderView = new CsvFileFinderView();
            CsvFileFinderController csvFileFinderController = new CsvFileFinderController(csvFileFinderView,
                    csvFileFinder, scanner);

            return csvFileFinderController;
        }
    }
}
