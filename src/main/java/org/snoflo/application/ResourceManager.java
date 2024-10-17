package org.snoflo.application;

import java.util.Scanner;

import org.snoflo.function.AppDataSource;
import org.snoflo.function.H2WebConsole;

import com.zaxxer.hikari.HikariDataSource;

public class ResourceManager {

    private static H2WebConsole console;
    private HikariDataSource dataSource;
    private Scanner scanner;

    public ResourceManager() {
        this.dataSource = AppDataSource.getInstance();
        this.scanner = new Scanner(System.in);
    }

    static {
        console = new H2WebConsole();
        console.connect();
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void close() {
        console.stop();
        this.dataSource.close();
        this.scanner.close();
    }

}
