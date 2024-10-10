package org.snoflo.application;

import java.util.Scanner;

import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.controller.StartController;
import org.snoflo.function.AppDataSource;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.function.H2WebConsole;
import org.snoflo.function.RandomQuestion;
import org.snoflo.repository.FinderRepositoryImpl;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.repository.QuestionRepositoryImpl;
import org.snoflo.view.FinderView;
import org.snoflo.view.MainView;
import org.snoflo.view.QuestionView;

import org.snoflo.service.FinderServiceImpl;
import org.snoflo.service.FinderService;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import com.zaxxer.hikari.HikariDataSource;

public class ApplicationBuilder {

    private final H2WebConsole h2Console;

    private final Scanner scanner;

    private final CsvFileParser csvFileParser;
    private final CsvFileFinder csvFileFinder;

    private final RandomQuestion randomQuestion;

    private final HikariDataSource dataSource;

    // --------------------
    // FinderService
    private final FinderRepository finderRepository;
    private final FinderService finderService;

    // QuestionService
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    // --------------------
    // view
    private final FinderView finderView;
    private final QuestionView questionView;
    private final MainView mainView;

    // 시작
    private final FinderController finderController;
    private final QuestionController questionController;
    private final StartController startController;
    // ----------------------------------------

    public static class AppBuilder {


    }

}

