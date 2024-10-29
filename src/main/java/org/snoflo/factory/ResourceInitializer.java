package org.snoflo.factory;

import java.util.Scanner;

import org.snoflo.function.AppDataSource;
import org.snoflo.function.H2WebConsole;

import com.zaxxer.hikari.HikariDataSource;

public class ResourceInitializer {

    private H2WebConsole console;
	private HikariDataSource dataSource;
    private Scanner scanner;

    public ResourceInitializer() {
        this.console = new H2WebConsole();
        this.dataSource = AppDataSource.getInstance();
        this.scanner = new Scanner(System.in);
    }
    
    public H2WebConsole getConsole() {
        return this.console;
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public Scanner getScanner() {
        return scanner;
    }

}
