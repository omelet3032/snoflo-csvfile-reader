package org.snoflo.application;

import java.util.Scanner;

import org.snoflo.controller.FinderController;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.FinderRepositoryImpl;
import org.snoflo.service.FinderService;
import org.snoflo.service.FinderServiceImpl;
import org.snoflo.view.FinderView;

import com.zaxxer.hikari.HikariDataSource;

public class FinderFactory {
    
    public FinderController getFinderController(ResourceManager resourceManager) {
         HikariDataSource hikariDataSource = resourceManager.getDataSource();
        Scanner scanner = resourceManager.getScanner();

        CsvFileParser csvFileParser = new CsvFileParser();
        CsvFileFinder csvFileFinder = new CsvFileFinder();

        FinderRepository finderRepository = new FinderRepositoryImpl(hikariDataSource);
        FinderService finderService = new FinderServiceImpl(csvFileParser, finderRepository);
        FinderView finderView = new FinderView();
        FinderController finderController = new FinderController(scanner, csvFileFinder, finderService, finderView);

        return finderController;
    }
}
