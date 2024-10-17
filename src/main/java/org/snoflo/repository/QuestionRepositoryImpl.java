package org.snoflo.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import org.snoflo.domain.RandomFields;

import com.zaxxer.hikari.HikariDataSource;

public class QuestionRepositoryImpl implements QuestionRepository {

    private HikariDataSource dataSource;

    public QuestionRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<RandomFields> findAll(String selectedFile) {

        List<RandomFields> list = new ArrayList<>();

        try (Connection conection = getDataSource().getConnection()) {

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT concept, description ")
                    .append("FROM " + selectedFile);
            PreparedStatement statement = conection.prepareStatement(sql.toString());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String concept = rs.getString("concept");
                String description = rs.getString("description");

                list.add(new RandomFields(concept, description));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> findAllTable() {
        List<String> list = new ArrayList<>();

        try (Connection conn = getDataSource().getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, "PUBLIC", "%", new String[] { "TABLE" });

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                list.add(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HikariDataSource getDataSource() {
        return this.dataSource;
    }

}
