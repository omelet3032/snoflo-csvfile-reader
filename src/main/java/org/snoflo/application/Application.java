package org.snoflo.application;

import java.io.IOException;
import java.util.Scanner;

import org.snoflo.builder.FinderSystemBuilder;
import org.snoflo.builder.AppSystemBuilder;
import org.snoflo.builder.QuestionSystemBuilder;
import org.snoflo.controller.AppEntry;
// import org.snoflo.controller.ControllerContext;
// import org.snoflo.controller.ControllerStrategy;
import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.factory.AppFactory;
import org.snoflo.factory.FinderFactory;
import org.snoflo.factory.QuestionFactory;
import org.snoflo.function.AppDataSource;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.function.H2WebConsole;
import org.snoflo.function.RandomQuestion;
import org.snoflo.repository.FinderRepositoryImpl;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.QuestionRepository;
import org.snoflo.repository.QuestionRepositoryImpl;
import org.snoflo.service.FinderServiceImpl;
import org.snoflo.service.FinderService;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.system.AppSystem;
import org.snoflo.system.FinderSystem;
import org.snoflo.system.QuestionSystem;
import org.snoflo.view.FinderView;
import org.snoflo.view.MainView;
import org.snoflo.view.QuestionView;

import com.zaxxer.hikari.HikariDataSource;

public class Application {

    // app
    
    // common
    
    // --------------------
    // view
    
    FinderFactory finderFactory = new FinderFactory();
    QuestionFactory questionFactory = new QuestionFactory();
    // ----------------------------------------
    
    
    public void start() {
        H2WebConsole h2Console = new H2WebConsole();
        Scanner scanner = new Scanner(System.in);
        HikariDataSource dataSource = AppDataSource.getInstance();
        ApplicationContext context = new ApplicationContext();
        


        context.setConsole(h2Console);
        context.connectH2WebConsole();
        
        
        AppSystem finderSystem = new FinderFactory().createSystem();
        AppSystem questionSystem = new QuestionFactory().createSystem();
        
        AppEntry entry = new AppEntry(scanner, finderSystem, questionSystem);
        
        context.setStrategy(entry);
        context.startController();
        
        
        context.close();;
    }

}
