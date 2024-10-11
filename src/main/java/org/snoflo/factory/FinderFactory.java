package org.snoflo.factory;

import java.util.Scanner;

import org.snoflo.builder.FinderControllerBuilder;
import org.snoflo.controller.FinderController;
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
    public FinderFactory createComponent() {

        this.hikariDataSource = AppDataSource.getInstance();
        this.scanner = new Scanner(System.in);
        this.csvFileParser = new CsvFileParser();
        this.csvFileFinder = new CsvFileFinder();

        this.finderRepository = new FinderRepositoryImpl(hikariDataSource);
        this.finderService = new FinderServiceImpl(finderRepository);
        this.finderView = new FinderView();
        this.finderController = new FinderController(scanner, csvFileParser, csvFileFinder, finderService, finderView);

        return this;
    }

    @Override
    public FinderControllerBuilder createBuilder() {

        FinderControllerBuilder finderBuilder = new FinderControllerBuilder.Builder()
        .dataSource(hikariDataSource) // 전역적으로 사용하는건데 builder로 넣어버리면 컨트롤러별로 다른 dataSource를 사용하는건가라는 생각을 할 수 있을 것 같다.
        .scanner(scanner)
        .csvFileParser(csvFileParser)
        .csvFileFinder(csvFileFinder)
        .repository(finderRepository)
        .service(finderService)
        .view(finderView)
        .controller(finderController)
        .build();

        return finderBuilder;
    }

    public void finderContollerStart() {
        finderController.start();
    }
}
