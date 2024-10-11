package org.snoflo.builder;

import org.snoflo.controller.AppController;
import org.snoflo.controller.FinderController;
import org.snoflo.repository.AppRepository;
import org.snoflo.repository.FinderRepository;
import org.snoflo.service.AppService;
import org.snoflo.service.FinderService;
import org.snoflo.view.AppView;
import org.snoflo.view.FinderView;

import com.zaxxer.hikari.HikariDataSource;

import java.util.Scanner;

import org.snoflo.builder.FinderControllerBuilder.Builder;

public interface AppBuilder {

    public Builder dataSoruce(HikariDataSource dataSource);

    public Builder scanner(Scanner scanner);

}
