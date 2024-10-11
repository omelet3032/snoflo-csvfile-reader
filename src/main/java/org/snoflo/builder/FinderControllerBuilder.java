package org.snoflo.builder;

import java.util.Scanner;

import org.snoflo.controller.FinderController;
import org.snoflo.controller.FinderController;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.repository.FinderRepository;
import org.snoflo.repository.FinderRepository;
import org.snoflo.service.FinderService;
import org.snoflo.service.FinderService;
import org.snoflo.view.FinderView;
import org.snoflo.view.FinderView;

import com.zaxxer.hikari.HikariDataSource;

public class FinderControllerBuilder implements AppControllerBuilder {

    private final HikariDataSource dataSource;
    private final Scanner scanner;

    private final CsvFileParser csvFileParser;
    private final CsvFileFinder csvFileFinder;

    private final FinderRepository finderRepository;
    private final FinderService finderService;
    private final FinderView finderView;

    private final FinderController finderController;

    private FinderControllerBuilder(Builder builder) {
        this.dataSource = builder.dataSource;
        this.scanner = builder.scanner;
        this.csvFileParser = builder.csvFileParser;
        this.csvFileFinder = builder.csvFileFinder;
        this.finderRepository = builder.finderRepository;
        this.finderService = builder.finderService;
        this.finderView = builder.finderView;
        this.finderController = builder.finderController;
    }

    @Override
    public void start() {
        finderController.start();
    }

    public static class Builder {

        private HikariDataSource dataSource;
        private Scanner scanner;

        private CsvFileParser csvFileParser;
        private CsvFileFinder csvFileFinder;

        private FinderRepository finderRepository;
        private FinderService finderService;
        private FinderView finderView;

        private FinderController finderController;


        public Builder dataSource(HikariDataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public Builder scanner(Scanner scanner) {
            this.scanner = scanner;
            return this;
        }

        public Builder csvFileParser(CsvFileParser csvFileParser) {
            this.csvFileParser = csvFileParser;
            return this;
        }

        public Builder csvFileFinder(CsvFileFinder csvFileFinder) {
            this.csvFileFinder = csvFileFinder;
            return this;
        }

        public Builder repository(FinderRepository finderRepository) {
            this.finderRepository = finderRepository;
            return this;
        }

     
        public Builder service(FinderService finderService) {
            this.finderService = finderService;
            return this;
        }

      
        public Builder view(FinderView finderView) {
            this.finderView = finderView;
            return this;
        }

       
        public Builder controller(FinderController finderController) {
            this.finderController = finderController;
            return this;
        }

        public FinderControllerBuilder build() {
            return new FinderControllerBuilder(this);
        }


		
    }

}
