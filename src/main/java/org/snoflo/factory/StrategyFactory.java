package org.snoflo.factory;

import java.lang.reflect.Proxy;
import java.util.Scanner;

import org.snoflo.application.ResourceHandler;
import org.snoflo.application.ResourceInitializer;
import org.snoflo.controller.FileFinderController;
import org.snoflo.controller.FileRegisterController;
import org.snoflo.controller.EntryController;
import org.snoflo.controller.RandomQuizController;
import org.snoflo.exception.CustomExceptionHandler;
import org.snoflo.function.FileFinder;
import org.snoflo.function.FileParser;
import org.snoflo.function.RandomQuiz;
import org.snoflo.repository.FileRegisterRepository;
import org.snoflo.repository.FileRegisterRepositoryImpl;
import org.snoflo.repository.RandomQuizRepository;
import org.snoflo.repository.RandomQuizRepositoryImpl;
import org.snoflo.repository.TableRepository;
import org.snoflo.repository.TableRepositoryImpl;
import org.snoflo.service.FileRegisterService;
import org.snoflo.service.FileRegisterServiceImpl;
import org.snoflo.service.RandomQuizService;
import org.snoflo.service.RandomQuizServiceImpl;
import org.snoflo.strategy.AppContext;
import org.snoflo.strategy.AppStrategy;
import org.snoflo.strategy.FileManagerStrategy;
import org.snoflo.strategy.EntryStrategy;
import org.snoflo.strategy.RandomQuizStrategy;
import org.snoflo.view.FileFinderView;
import org.snoflo.view.FileRegisterView;
import org.snoflo.view.EntryView;
import org.snoflo.view.RandomQuizView;

import com.zaxxer.hikari.HikariDataSource;

public class StrategyFactory {

    private ResourceInitializer resourceInitializer;
    private ResourceHandler resourceHandler;

    private AppContext context;

    public StrategyFactory(AppContext context) {
        this.context = context;
        this.resourceInitializer = new ResourceInitializer();
        this.resourceHandler = new ResourceHandler();
    }

    public AppStrategy createEntryStrategy() {

        EntryController entryController = new ControllerFactory()
                .createEntryController(
                        createFileManagerStrategy(),
                        createRandomQuizStrategy());

        return new EntryStrategy(entryController, resourceHandler);
    }

    private AppStrategy createFileManagerStrategy() {
        FileFinderController finderController = new ControllerFactory().createFileFinderController();
        FileRegisterController registerController = new ControllerFactory().createFileRegisterController();
        AppStrategy fileManagerStrategy = new FileManagerStrategy(
                finderController, registerController);
        return fileManagerStrategy;
    }

    private AppStrategy createRandomQuizStrategy() {
        RandomQuizController randomQuizController = new ControllerFactory().createRandomQuizController();
        AppStrategy randomQuizStrategy = new RandomQuizStrategy(
                randomQuizController);
        return randomQuizStrategy;
    }

    private class ControllerFactory {

        private HikariDataSource hikariDataSource = resourceInitializer.getDataSource();
        private Scanner scanner = resourceInitializer.getScanner();

        private TableRepository tableRepository = new TableRepositoryImpl(hikariDataSource);

        public ControllerFactory() {
        }

        private EntryController createEntryController(AppStrategy fileManagerStrategy, AppStrategy randomQuizStrategy) {

            EntryView entryView = new EntryView();

            EntryController entryController = new EntryController(
                    context,
                    fileManagerStrategy,
                    randomQuizStrategy,
                    scanner,
                    entryView);

            return entryController;
        }

        private FileRegisterController createFileRegisterController() {

            FileParser fileParser = new FileParser();

            // TableRepository tableRepositoryProxy = (TableRepository)
            // Proxy.newProxyInstance(
            // TableRepository.class.getClassLoader(),
            // new Class[] { TableRepository.class },
            // new CustomExceptionHandler(tableRepository));

            // FileRegisterService registerServiceProxy = (FileRegisterService) Proxy.newProxyInstance(
            //         FileRegisterService.class.getClassLoader(),
            //         new Class[] { FileRegisterService.class },
            //         new CustomExceptionHandler(finderService));

            FileRegisterRepository finderRepository = new FileRegisterRepositoryImpl(hikariDataSource);

            FileRegisterService finderService = new FileRegisterServiceImpl(fileParser, finderRepository,
                    tableRepository);


            FileRegisterView registerView = new FileRegisterView();
            FileRegisterController finderController = new FileRegisterController(scanner, finderService,
                    registerView);

            return finderController;
        }

        private FileFinderController createFileFinderController() {
            FileFinder fileFinder = new FileFinder();

            FileFinderView fileFinderView = new FileFinderView();
            FileFinderController fileFinderController = new FileFinderController(fileFinderView,
                    fileFinder, scanner);

            return fileFinderController;
        }

        private RandomQuizController createRandomQuizController() {

            RandomQuiz randomQuiz = new RandomQuiz();

            RandomQuizRepository questionRepository = new RandomQuizRepositoryImpl(hikariDataSource);
            RandomQuizService questionService = new RandomQuizServiceImpl(questionRepository, tableRepository);
            RandomQuizView questionView = new RandomQuizView();
            RandomQuizController questionController = new RandomQuizController(scanner, randomQuiz,
                    questionService,
                    questionView);

            return questionController;
        }

    }
}
