package org.snoflo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * QuestionDatabase
 */
public class QuestionDatabase {

    private HikariConfig config;
    private HikariDataSource dataSource;

    public QuestionDatabase(HikariConfig config, HikariDataSource dataSource) {
        this.config = config;
        this.dataSource = dataSource;
    }

    public void createTable() {

        try (Connection connection = dataSource.getConnection()) {

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
            PreparedStatement stmt = connection.prepareStatement(createTableSql);

            stmt.execute(createTableSql);

            System.out.println("테이블 생성 완료");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}