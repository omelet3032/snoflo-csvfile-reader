package org.snoflo.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/snoflo_file_reader"; // MySQL 도커 컨테이너 주소 및
    private static final String USER = "snoflo"; // MySQL 사용자
    private static final String PASSWORD = "Snoflo123!!"; // MySQL 비밀번호

    private Connection connection;

    // 싱글톤 지연 초기화 적용
    private static class DBConnectionHolder {
        private static final DBConnection INSTANCE = new DBConnection();
    }

    public static DBConnection getInstance() {
        return DBConnectionHolder.INSTANCE;
    }

    public void connect() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.out.println("연결이 되지 않았습니다.");
            e.printStackTrace();
        }
    }

}
