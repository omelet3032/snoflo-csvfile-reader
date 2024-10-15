package org.snoflo.entry;

import java.util.Scanner;

import org.snoflo.function.H2WebConsole;

import com.zaxxer.hikari.HikariDataSource;

public class ResourceManager {

    private H2WebConsole console;
    private HikariDataSource dataSource;
    private Scanner scanner;

    public ResourceManager(H2WebConsole console, HikariDataSource dataSource, Scanner scanner) {
        this.console = console;
        this.dataSource = dataSource;
        this.scanner = scanner;
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public H2WebConsole getConsole() {
        return console;
    }
    
    public void close() {
        this.console.stop();
        this.dataSource.close();
        this.scanner.close();
    }


}
