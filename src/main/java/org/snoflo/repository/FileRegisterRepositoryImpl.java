package org.snoflo.repository;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.snoflo.domain.CsvFileRow;

import com.zaxxer.hikari.HikariDataSource;

public class FileRegisterRepositoryImpl implements FileRegisterRepository {

    private HikariDataSource dataSource;

    public FileRegisterRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(List<CsvFileRow> list, String fileName) {

        String insertSql = "INSERT INTO " + fileName + " (concept, description)" +
                " VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

            for (CsvFileRow question : list) {
                String concept = question.getConcept();
                String description = question.getDescription();

                preparedStatement.setString(1, concept);
                preparedStatement.setString(2, description);

                preparedStatement.addBatch();

            }

            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
