package org.snoflo.application;

import java.util.Scanner;

import org.snoflo.controller.EntryController;
import org.snoflo.commander.CsvFileManagerCommander;
import org.snoflo.commander.EntryCommander;
import org.snoflo.commander.RandomQuizCommander;
import org.snoflo.commander.AppCommander;

import org.snoflo.controller.CsvFileFinderController;
import org.snoflo.controller.CsvFileRegisterController;
import org.snoflo.controller.RandomQuizController;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
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

public class EntryCommanderFactory {

    private ResourceInitializer resourceInitializer;
    private ResourceHandler resourceHandler;

    public EntryCommanderFactory() {
        this.resourceInitializer = new ResourceInitializer();
        this.resourceHandler = new ResourceHandler();
    }

    public EntryCommander creatEntryCommander() {

        EntryController entryController = new ControllerFactory().createEntryController();

        return new EntryCommander(entryController, resourceHandler);
    }

    // private EntryController createEntryController() {

    // HikariDataSource hikariDataSource = resourceInitializer.getDataSource();
    // Scanner scanner = resourceInitializer.getScanner();

    // CsvFileFinderController csvFileFinderController = new
    // ControllerFactory(scanner)
    // .createCsvFileFinderController();
    // CsvFileRegisterController csvFileRegisterController = new
    // ControllerFactory(hikariDataSource, scanner)
    // .createCsvFileRegisterController();
    // RandomQuizController randomQuizController = new
    // ControllerFactory(hikariDataSource, scanner)
    // .createQuestionController();

    // AppCommander csvFileManagerCommander = new
    // CsvFileManagerCommander(csvFileFinderController,
    // csvFileRegisterController);
    // AppCommander randomQuizCommander = new
    // RandomQuizCommander(randomQuizController);

    // EntryController entryController = new EntryController(resourceHandler,
    // csvFileManagerCommander,
    // randomQuizCommander, scanner);

    // return entryController;
    // }

    private class ControllerFactory {

        private HikariDataSource hikariDataSource;
        private Scanner scanner;

        public ControllerFactory() {
        }

        public ControllerFactory(Scanner scanner) {
            this.scanner = scanner;
        }

        public ControllerFactory(HikariDataSource hikariDataSource, Scanner scanner) {
            this.hikariDataSource = hikariDataSource;
            this.scanner = scanner;
        }

        public EntryController createEntryController() {

            HikariDataSource hikariDataSource = resourceInitializer.getDataSource();
            Scanner scanner = resourceInitializer.getScanner();

            CsvFileFinderController csvFileFinderController = new ControllerFactory(scanner)
                    .createCsvFileFinderController();
            CsvFileRegisterController csvFileRegisterController = new ControllerFactory(hikariDataSource, scanner)
                    .createCsvFileRegisterController();
            RandomQuizController randomQuizController = new ControllerFactory(hikariDataSource, scanner)
                    .createQuestionController();

            AppCommander csvFileManagerCommander = new CsvFileManagerCommander(csvFileFinderController,
                    csvFileRegisterController);
            AppCommander randomQuizCommander = new RandomQuizCommander(randomQuizController);

            EntryController entryController = new EntryController(resourceHandler, csvFileManagerCommander,
                    randomQuizCommander, scanner);

            return entryController;
        }

        public CsvFileRegisterController createCsvFileRegisterController() {

            CsvFileParser csvFileParser = new CsvFileParser();

            FinderRepository finderRepository = new FinderRepositoryImpl(hikariDataSource);
            FinderService finderService = new FinderServiceImpl(csvFileParser, finderRepository);
            CsvFileRegisterView registerView = new CsvFileRegisterView();
            CsvFileRegisterController finderController = new CsvFileRegisterController(scanner, finderService,
                    registerView);

            return finderController;
        }

        public CsvFileFinderController createCsvFileFinderController() {
            CsvFileFinder csvFileFinder = new CsvFileFinder();

            CsvFileFinderView csvFileFinderView = new CsvFileFinderView();
            CsvFileFinderController csvFileFinderController = new CsvFileFinderController(csvFileFinderView,
                    csvFileFinder, scanner);

            return csvFileFinderController;
        }

        public RandomQuizController createQuestionController() {

            RandomQuiz randomQuiz = new RandomQuiz();

            QuestionRepository questionRepository = new QuestionRepositoryImpl(hikariDataSource);
            QuestionService questionService = new QuestionServiceImpl(questionRepository);
            QuestionView questionView = new QuestionView();
            RandomQuizController questionController = new RandomQuizController(scanner, randomQuiz,
                    questionService,
                    questionView);

            return questionController;
        }

    }
}
