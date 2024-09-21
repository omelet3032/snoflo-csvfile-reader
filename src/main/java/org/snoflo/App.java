package org.snoflo;

import java.io.IOException;

import org.snoflo.application.Application;
import org.snoflo.dbconnection.MySqlConfig;
import org.snoflo.dbconnection.MysqlConnection;

public class App {
    
    public static void main(String[] args) throws IOException {

        Application app = new Application();
        app.start();
    }
}
