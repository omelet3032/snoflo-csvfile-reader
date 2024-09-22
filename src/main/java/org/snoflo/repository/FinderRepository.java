package org.snoflo.repository;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.snoflo.domain.Question;

import com.zaxxer.hikari.HikariDataSource;

public class FinderRepository {

    private HikariDataSource dataSource;

    public FinderRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // public void save(List<Question> list) {

    // }

    // public void save(Path selectFile) {

    //     // List<Question> list = this.csvFileManager.generateData();

    //     // System.out.println(list.get(0).toString());
    //     try (Connection connection = dataSource.getConnection();) {

    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     // list 추출
    //     // 쿼리 작성 insert into ~

    // }

    // public void createH2Table() {
        
    //     String sql = """
    //             CREATE TABLE IF NOT EXISTS question (
    //                 id INTEGER IDENTITY,
    //                 concept VARCHAR(255) NOT NULL,
    //                 description VARCHAR(255) NOT NULL,
    //                 keyword1 VARCHAR(255),
    //                 keyword2 VARCHAR(255),
    //                 PRIMARY KEY(id)
    //             )
    //             """;

    //     try (Connection connection = dataSource.getConnection()) {

    //         PreparedStatement statement = connection.prepareStatement(sql);
    //         statement.execute(sql);


    //     } catch (SQLException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();

    //     }
    // }

    // public FinderRepository(CsvFileManager csvFileManager, MysqlConnection
    // dbConnection) {
    // this.csvFileManager = csvFileManager;
    // this.dbConnection = dbConnection;
    // dbConnection.connect();
    // }

    // public void save(Path selectedFile) {
    // this.selectedFile = selectedFile;
    // this.csvFileManager.generateData(selectedFile);
    // }

    // public void save2(Path selectedFile) {
    // Connection connection = dbConnection.getConnection();

    // try (Statement statement = connection.createStatement()) {

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

}
