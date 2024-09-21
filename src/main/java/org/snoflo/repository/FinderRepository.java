package org.snoflo.repository;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.snoflo.db.CsvFileManager;
import org.snoflo.dbconnection.MysqlConnection;
import org.snoflo.domain.Question;

import com.zaxxer.hikari.HikariDataSource;

public class FinderRepository {

    private MysqlConnection dbConnection;
    private HikariDataSource dataSource;

    private CsvFileManager csvFileManager;

    // private Path selectedFile;

    public FinderRepository(CsvFileManager csvFileManager, HikariDataSource dataSource) {
        this.csvFileManager = csvFileManager;
        this.dataSource = dataSource;
    }

    public FinderRepository(CsvFileManager csvFileManager, MysqlConnection dbConnection) {
        this.csvFileManager = csvFileManager;
        this.dbConnection = dbConnection;
        dbConnection.connect();
    }

    // public void save(Path selectedFile) {
    // this.selectedFile = selectedFile;
    // this.csvFileManager.generateData(selectedFile);
    // }

    public void save2(Path selectedFile) {
        Connection connection = dbConnection.getConnection();

        try (Statement statement = connection.createStatement()) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Path selectedFile) {

        List<Question> list = this.csvFileManager.generateData(selectedFile);

        try (Connection connection = dataSource.getConnection();) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // list 추출
        // 쿼리 작성 insert into ~

    }
}
