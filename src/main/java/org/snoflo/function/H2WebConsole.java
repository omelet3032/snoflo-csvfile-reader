package org.snoflo.function;

import java.sql.SQLException;

import org.h2.tools.Server;

public class H2WebConsole {

    private Server webServer = new Server();

    public void connect() {
        try {
            webServer = Server.createWebServer("-webAllowOthers", "-webPort", "8082").start();
            // Server tcpServer = Server.createTcpServer("-tcpAllowOthers", "-tcpPort",
            // "9092").start();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        webServer.stop();
    }

}
