package org.snoflo;

import java.io.IOException;

import org.snoflo.application.Application;
import org.snoflo.dbconnection.DBConfig;
import org.snoflo.dbconnection.DBConnection;

public class App {
    
    public static void main(String[] args) throws IOException {

        DBConfig config = new DBConfig();
        
        config.getJdbcUrl();
        System.out.println(config.getUser());
        Application app = new Application();
        app.start();
    }
}
