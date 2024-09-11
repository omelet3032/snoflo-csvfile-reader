package org.snoflo;

import org.snoflo.application.Application;
import org.snoflo.controller.QuestionController;

public class App {
    
    public static void main(String[] args) {

        Application.start(new QuestionController());

    }
}
