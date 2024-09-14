package org.snoflo;

import java.io.IOException;

import org.snoflo.application.Application;
import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;
import org.snoflo.repository.QuestionDataConverter;
import org.snoflo.service.CsvFilesFinderService;
import org.snoflo.service.QuestionServiceImpl;

public class App {
    
    public static void main(String[] args) throws IOException {

        // Application.start(new QuestionController(new CsvFilesFinderService(), new QuestionServiceImpl()));
        // Application app = new Application(new QuestionController(new QuestionServiceImpl()));
        Application app = new Application();
        app.start();
    }
}
