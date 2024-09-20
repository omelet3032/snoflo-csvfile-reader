package org.snoflo.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase"; // MySQL 도커 컨테이너 주소 및 데이터베이스명
    private static final String USER = "root"; // MySQL 사용자
    private static final String PASSWORD = "password"; // MySQL 비밀번호

    private static Connection connection;
    private static Statement statement;
    
    public static void connect() {

        connection = null;
        statement = null;
        try {
            // MySQL JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // MySQL 서버에 연결
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // 쿼리 실행
            statement = connection.createStatement();
            String query = "SELECT * FROM users"; // 예시 쿼리
            ResultSet resultSet = statement.executeQuery(query);

            // 결과 출력
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
