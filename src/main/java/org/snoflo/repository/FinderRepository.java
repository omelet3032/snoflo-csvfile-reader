package org.snoflo.repository;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.snoflo.domain.Question;

import com.zaxxer.hikari.HikariDataSource;

public class FinderRepository {

    private HikariDataSource dataSource;

    public FinderRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
        // createTable();
    }

    public void save(List<Question> list) {
        // QuestionDatabase db = new QuestionDatabase(dataSource);

        String insertSql = """
                INSERT INTO question (concept, description)
                VALUES (?, ?)
                """;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

            for (Question question : list) {
                String concept = question.getConcept();
                String description = question.getDescription();

                preparedStatement.setString(1, concept);
                preparedStatement.setString(2, description);

                // preparedStatement.addBatch();
                preparedStatement.execute();

            }
            
            // preparedStatement.executeBatch();

            System.out.println("저장 완료");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
