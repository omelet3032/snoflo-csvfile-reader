package org.snoflo.application;

import java.util.Scanner;

import org.snoflo.function.H2WebConsole;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ApplicationContext {

    private ApplicationStrategy strategy;

    private H2WebConsole console;

    private Scanner scanner;

    private HikariConfig config;

    private HikariDataSource dataSource;

    
    public void setConsole(H2WebConsole console) {
        this.console = console;
    }
   
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public void setConfig(HikariConfig config) {
        this.config = config;
    }

    public void setDataSource(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setStrategy(ApplicationStrategy strategy) {
        this.strategy = strategy;
    }

    public void startController() {
        strategy.start();
    }

    public void connectH2WebConsole() {
        console.connect();
    }

    public void stopH2WebConsole() {
        console.stop();
    }

    public void start() {
    }

}
