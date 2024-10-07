package org.snoflo.repository;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.snoflo.domain.Question;

import com.zaxxer.hikari.HikariDataSource;

public class FinderRepositoryImpl implements FinderRepository {

    private HikariDataSource dataSource;

    public FinderRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(List<Question> list, Path selectedFilePath) {
        String fileName = selectedFilePath.getFileName().toString();
        fileName = fileName.replace(".csv", "");
        fileName = fileName.toLowerCase();

        String insertSql = "INSERT INTO " + fileName + " (concept, description)" +
                " VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

            for (Question question : list) {
                String concept = question.getConcept();
                String description = question.getDescription();

                preparedStatement.setString(1, concept);
                preparedStatement.setString(2, description);

                preparedStatement.addBatch();
                // preparedStatement.execute();

            }

            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public HikariDataSource getDataSource() {
        return this.dataSource;
    }

    // public void save(List<Question> list) {
    // // QuestionDatabase db = new QuestionDatabase(dataSource);

    // String insertSql = """
    // INSERT INTO question (concept, description)
    // VALUES (?, ?)
    // """;

    // try (Connection connection = dataSource.getConnection()) {
    // PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

    // for (Question question : list) {
    // String concept = question.getConcept();
    // String description = question.getDescription();

    // preparedStatement.setString(1, concept);
    // preparedStatement.setString(2, description);

    // // preparedStatement.addBatch();
    // preparedStatement.execute();

    // }

    // // preparedStatement.executeBatch();

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }

    // }

}
