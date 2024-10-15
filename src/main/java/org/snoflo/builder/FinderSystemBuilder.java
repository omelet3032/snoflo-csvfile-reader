package org.snoflo.builder;

import java.util.Scanner;

import org.snoflo.controller.FinderController;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.repository.AppRepository;
import org.snoflo.repository.FinderRepository;
import org.snoflo.service.FinderService;
import org.snoflo.view.FinderView;
import org.snoflo.builder.CommonNestedBuilder;
import org.snoflo.builder.AppSystemBuilder;

import com.zaxxer.hikari.HikariDataSource;

public class FinderSystemBuilder implements AppSystemBuilder {

    private final HikariDataSource dataSource;
    private final Scanner scanner;

    private CsvFileParser csvFileParser;
    private CsvFileFinder csvFileFinder;

    private final FinderRepository finderRepository;
    private final FinderService finderService;
    private final FinderView finderView;

    private final FinderController finderController;

    private FinderSystemBuilder(Builder builder) {
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
    public FinderController getController() {
        return this.finderController;
    }

    public static class Builder
            implements CommonNestedBuilder<FinderRepository, FinderService, FinderView, FinderController> {

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

        public Builder input(Scanner scanner) {
            this.scanner = scanner;
            return this;
        }

        public Builder functionWithService(CsvFileParser csvFileParser) {
            this.csvFileParser = csvFileParser;
            return this;
        }

        public Builder functionWithController(CsvFileFinder csvFileFinder) {
            this.csvFileFinder = csvFileFinder;
            return this;
        }
        @Override
        public Builder repository(FinderRepository finderRepository) {
            this.finderRepository = finderRepository;
            return this;
        }

        @Override
        public Builder service(FinderService finderService) {
            this.finderService = finderService;
            return this;
        }

        @Override
        public Builder view(FinderView finderView) {
            this.finderView = finderView;
            return this;
        }

        @Override
        public Builder controller(FinderController finderController) {
            this.finderController = finderController;
            return this;
        }

        public FinderSystemBuilder build() {
            return new FinderSystemBuilder(this);
        }

    }

}
