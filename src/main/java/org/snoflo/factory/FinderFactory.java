package org.snoflo.factory;

import java.util.Scanner;

import org.snoflo.builder.FinderSystemBuilder;
import org.snoflo.controller.FinderController;
import org.snoflo.entry.ResourceManager;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.FinderRepositoryImpl;
import org.snoflo.service.FinderService;
import org.snoflo.service.FinderServiceImpl;
import org.snoflo.system.AppSystem;
import org.snoflo.system.FinderSystem;
import org.snoflo.view.FinderView;

import com.zaxxer.hikari.HikariDataSource;

public class FinderFactory extends AppFactory {

    private HikariDataSource hikariDataSource;
    private Scanner scanner;

    private CsvFileParser csvFileParser;
    private CsvFileFinder csvFileFinder;

    private FinderRepository finderRepository;
    private FinderService finderService;
    private FinderView finderView;
    private FinderController finderController;

    @Override
    protected AppSystem createProduct(ResourceManager resourceManager) {
        initialize(resourceManager);

        FinderSystemBuilder finderSystemBuilder = new FinderSystemBuilder.Builder()
                .dataSource(hikariDataSource)
                .input(scanner)
                .functionWithService(csvFileParser)
                .functionWithController(csvFileFinder)
                .repository(finderRepository)
                .service(finderService)
                .view(finderView)
                .controller(finderController)
                .build();

        FinderSystem finderSystem = new FinderSystem(finderSystemBuilder);

        return finderSystem;

    }

    @Override
    protected void initialize(ResourceManager resourceManager) {

        this.hikariDataSource = resourceManager.getDataSource();
        this.scanner = resourceManager.getScanner();

        this.csvFileParser = new CsvFileParser();
        this.csvFileFinder = new CsvFileFinder();

        this.finderRepository = new FinderRepositoryImpl(hikariDataSource);
        this.finderService = new FinderServiceImpl(csvFileParser, finderRepository);
        this.finderView = new FinderView();
        this.finderController = new FinderController(scanner, csvFileFinder, finderService, finderView);

    }

}
