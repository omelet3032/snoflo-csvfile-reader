package org.snoflo.factory;

import java.util.Scanner;

import org.snoflo.function.H2WebConsole;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ApplicationContext {

    private SimpleIoCContainer container;

    public ApplicationContext(SimpleIoCContainer container) {
        this.container = container;
    }

    private void createSingtonObject() {
        container.registerSingleton(Scanner.class, new Scanner(System.in));
        container.registerSingleton(HikariDataSource.class,
                new HikariDataSource(new HikariConfig("/application-h2.properties")));
        container.registerSingleton(H2WebConsole.class, new H2WebConsole());
    }
}
