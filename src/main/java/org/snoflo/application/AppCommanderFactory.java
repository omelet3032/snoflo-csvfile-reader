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
import org.snoflo.function.AppDataSource;
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
import org.snoflo.view.EntryView;
import org.snoflo.view.QuestionView;

import com.zaxxer.hikari.HikariDataSource;

public class AppCommanderFactory {

    private ResourceInitializer resourceInitializer;
    private ResourceHandler resourceHandler;

    public AppCommanderFactory() {
        this.resourceInitializer = new ResourceInitializer();
        this.resourceHandler = new ResourceHandler();
    }

    public AppCommander creatEntryCommander() {

        EntryController entryController = new ControllerFactory().createEntryController();

        return new EntryCommander(entryController, resourceHandler);
    }

    private class ControllerFactory {

        private HikariDataSource hikariDataSource = resourceInitializer.getDataSource();
        private Scanner scanner = resourceInitializer.getScanner();

        public EntryController createEntryController() {

            EntryView entryView = new EntryView();

            CsvFileFinderController csvFileFinderController = new ControllerFactory()
                    .createCsvFileFinderController();
            CsvFileRegisterController csvFileRegisterController = new ControllerFactory()
                    .createCsvFileRegisterController();
            RandomQuizController randomQuizController = new ControllerFactory()
                    .createQuestionController();

            AppCommander csvFileManagerCommander = new CsvFileManagerCommander(csvFileFinderController,
                    csvFileRegisterController);
            AppCommander randomQuizCommander = new RandomQuizCommander(randomQuizController);

            EntryController entryController = new EntryController(csvFileManagerCommander,
                    randomQuizCommander, scanner, entryView);

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
