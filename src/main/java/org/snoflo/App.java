package org.snoflo;

import java.io.IOException;

import org.snoflo.application.Application;

public class App {
    
    public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {

        Application app = new Application();
        app.start();
        
    }
}
