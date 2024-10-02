package org.snoflo.repository;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * QuestionDatabase
 */
public class QuestionDatabase {

    private HikariDataSource dataSource;

    public QuestionDatabase(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createTable() {

        try (Connection connection = dataSource.getConnection()) {

            Statement stmt = connection.createStatement();

            String createTableSql = """
                    CREATE TABLE IF NOT EXISTS question (
                        id INTEGER IDENTITY,
                        concept VARCHAR(255) NOT NULL,
                        description VARCHAR(255) NOT NULL,
                        keyword1 VARCHAR(255),
                        keyword2 VARCHAR(255),
                        PRIMARY KEY (id)
                    )
                    """;

            stmt.execute(createTableSql);

            System.out.println("테이블 생성 완료");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}