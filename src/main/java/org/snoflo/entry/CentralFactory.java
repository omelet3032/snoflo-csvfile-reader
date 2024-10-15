package org.snoflo.entry;

import java.util.Scanner;

import org.snoflo.factory.FinderFactory;
import org.snoflo.factory.QuestionFactory;
import org.snoflo.function.AppDataSource;
import org.snoflo.function.H2WebConsole;
import org.snoflo.system.AppSystem;

import com.zaxxer.hikari.HikariDataSource;

public class CentralFactory {

    public AppEntry createSystem() {

        H2WebConsole console = new H2WebConsole();
        HikariDataSource dataSource = AppDataSource.getInstance();
        Scanner scanner = new Scanner(System.in);

        ResourceManager resourceManager = new ResourceManager(console, dataSource, scanner);

        AppSystem finderSystem = new FinderFactory().createOperation(resourceManager);
        AppSystem questionSystem = new QuestionFactory().createOperation(resourceManager);
        
        AppEntry appEntry = new AppEntry(resourceManager, finderSystem, questionSystem);

        return appEntry;
    }
}
