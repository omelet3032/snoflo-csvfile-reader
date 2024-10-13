package org.snoflo.factory;

import java.util.Scanner;

import org.snoflo.builder.AppSystemBuilder;
import org.snoflo.builder.FinderSystemBuilder;
import org.snoflo.controller.FinderController;
import org.snoflo.function.AppDataSource;
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

public class FinderFactory implements AppFactory {

    @Override
    public AppSystem createSystem() {

        HikariDataSource hikariDataSource = AppDataSource.getInstance();
        Scanner scanner = new Scanner(System.in);
    
        FinderRepository finderRepository = new FinderRepositoryImpl(hikariDataSource);
        FinderService finderService = new FinderServiceImpl(finderRepository);
        FinderView finderView = new FinderView();
        FinderController finderController = new FinderController(scanner, finderService, finderView);
    
        FinderSystemBuilder finderSystemBuilder = new FinderSystemBuilder.Builder()
                .dataSource(hikariDataSource)
                .scanner(scanner)
                .repository(finderRepository)
                .service(finderService)
                .view(finderView)
                .controller(finderController)
                .build();
                
        FinderSystem finderSystem = new FinderSystem(finderSystemBuilder);
        
        return finderSystem;
    }

}
