package org.snoflo.application;

import java.util.Scanner;

import org.snoflo.controller.FinderController;
import org.snoflo.function.AppDataSource;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.FinderRepositoryImpl;
import org.snoflo.service.FinderService;
import org.snoflo.service.FinderServiceImpl;
import org.snoflo.view.FinderView;

import com.zaxxer.hikari.HikariDataSource;

public class FinderFactory implements AppFactory {
    
    private HikariDataSource hikariDataSource;
    private Scanner scanner;

    private CsvFileParser csvFileParser;
    private CsvFileFinder csvFileFinder;

    private FinderRepository finderRepository;
    private FinderService finderService;
    private FinderView finderView;

    private FinderController finderController;

    @Override
    public AppFactory createComponent() {

        this.hikariDataSource = AppDataSource.getInstance();
        this.scanner = new Scanner(System.in);
        this.csvFileParser = new CsvFileParser();
        this.csvFileFinder = new CsvFileFinder();

        this.finderRepository = new FinderRepositoryImpl(dataSource);
        this.finderService = new FinderServiceImpl(finderRepository);
        this.finderView = new FinderView();
        this.finderController = new FinderController(scanner, csvFileParser, csvFileFinder, finderService, finderView);

        return this;
    }

    @Override
    public FinderBuilder createBuilder() {

        FinderBuilder finderBuilder = new FinderBuilder.Builder()
        .dataSource(dataSource)
        .scanner(scanner)
        .csvFileParser(csvFileParser)
        .csvFileFinder(csvFileFinder)
        .finderRepository(finderRepository)
        .finderService(finderService)
        .finderView(finderView)
        .finderController(finderController)
        .build();

        // FinderBuilder builder = new FinderBuilder.Builder(dataSource, scanner, csvFileParser, csvFileFinder, finderRepository, finderService, finderView, finderController).build();
        // FinderBuilder builder = new FinderBuilder.Builder(createComponent()).build();

        return finderBuilder;
    }

}
