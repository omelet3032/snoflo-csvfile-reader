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
import org.snoflo.repository.FileRegisterRepository;
import org.snoflo.repository.FileRegisterRepositoryImpl;
import org.snoflo.repository.RandomQuizRepository;
import org.snoflo.repository.RandomQuizRepositoryImpl;
import org.snoflo.service.FileRegisterService;
import org.snoflo.service.FileRegisterServiceImpl;
import org.snoflo.service.RandomQuizService;
import org.snoflo.service.RandomQuizServiceImpl;
import org.snoflo.view.CsvFileFinderView;
import org.snoflo.view.CsvFileRegisterView;
import org.snoflo.view.EntryView;
import org.snoflo.view.RandomQuizView;

import com.zaxxer.hikari.HikariDataSource;

public class AppCommanderFactory {

    private ResourceInitializer resourceInitializer;
    private ResourceHandler resourceHandler;

    public AppCommanderFactory() {
        this.resourceInitializer = new ResourceInitializer();
        this.resourceHandler = new ResourceHandler();
    }

    public AppCommander createEntryCommander() {

        EntryController entryController = new ControllerFactory().createEntryController();

        return new EntryCommander(entryController, resourceHandler);
    }


    private class ControllerFactory {

        private HikariDataSource hikariDataSource = resourceInitializer.getDataSource();
        private Scanner scanner = resourceInitializer.getScanner();

        public EntryController createEntryController() {

            EntryView entryView = new EntryView();

            CsvFileFinderController csvFileFinderController = createCsvFileFinderController();
            CsvFileRegisterController csvFileRegisterController = createCsvFileRegisterController();
            RandomQuizController randomQuizController = createQuestionController();

            AppCommander csvFileManagerCommander = new CsvFileManagerCommander(csvFileFinderController,
                    csvFileRegisterController);
            AppCommander randomQuizCommander = new RandomQuizCommander(randomQuizController);

            EntryController entryController = new EntryController(csvFileManagerCommander,
                    randomQuizCommander, scanner, entryView);

            return entryController;
        }

        private CsvFileRegisterController createCsvFileRegisterController() {

            CsvFileParser csvFileParser = new CsvFileParser();

            FileRegisterRepository finderRepository = new FileRegisterRepositoryImpl(hikariDataSource);
            FileRegisterService finderService = new FileRegisterServiceImpl(csvFileParser, finderRepository);
            CsvFileRegisterView registerView = new CsvFileRegisterView();
            CsvFileRegisterController finderController = new CsvFileRegisterController(scanner, finderService,
                    registerView);

            return finderController;
        }

        private CsvFileFinderController createCsvFileFinderController() {
            CsvFileFinder csvFileFinder = new CsvFileFinder();

            CsvFileFinderView csvFileFinderView = new CsvFileFinderView();
            CsvFileFinderController csvFileFinderController = new CsvFileFinderController(csvFileFinderView,
                    csvFileFinder, scanner);

            return csvFileFinderController;
        }

        private RandomQuizController createQuestionController() {

            RandomQuiz randomQuiz = new RandomQuiz();

            RandomQuizRepository questionRepository = new RandomQuizRepositoryImpl(hikariDataSource);
            RandomQuizService questionService = new RandomQuizServiceImpl(questionRepository);
            RandomQuizView questionView = new RandomQuizView();
            RandomQuizController questionController = new RandomQuizController(scanner, randomQuiz,
                    questionService,
                    questionView);

            return questionController;
        }

    }
}
