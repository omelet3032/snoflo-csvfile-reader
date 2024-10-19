package org.snoflo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snoflo.TestDbCreator;
import org.snoflo.domain.CsvFileRow;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class RepositoryTests {

    private RandomQuizRepositoryImpl questionRepository;
    private FileRegisterRepositoryImpl finderRepository;
    private HikariDataSource dataSource;

    // @BeforeEach
    // public void setup() {
    // System.out.println("테스트 setup 메서드");
    // HikariConfig config = new HikariConfig("/application-h2.properties");

    // dataSource = new HikariDataSource(config);

    // finderRepository = new FinderRepository(dataSource);
    // }

    @BeforeEach
    public void setup() {
        System.out.println("테스트 setup 메서드");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:question");
        config.setUsername("sa");
        config.setPassword("");
        dataSource = new HikariDataSource(config);

        new TestDbCreator(dataSource);
        finderRepository = new FileRegisterRepositoryImpl(dataSource);
        questionRepository = new RandomQuizRepositoryImpl(dataSource);
    }

    @Test
    public void testSave() {
        System.out.println("testSave() 메서드");
        List<CsvFileRow> questionList = new ArrayList<>();
        questionList.add(new CsvFileRow("빌더", "객체 조립"));
        questionList.add(new CsvFileRow("싱글톤", "전역 변수"));
        questionList.add(new CsvFileRow("프록시", "대리 객체"));

        // finderRepository.save(questionList);

        System.out.println("테스트 성공?");

        try (var connection = dataSource.getConnection()) {

            var statement = connection.createStatement();
            var resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM question");
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                // assertEquals(3, count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            fail("데이터 조회 실패");
        }

    }

    @Test
    public void TestfindAll() {
        System.out.println("TestfindAll 메서드 진입");

        List<CsvFileRow> questionList = new ArrayList<>();
        questionList.add(new CsvFileRow("빌더", "객체 조립"));
        questionList.add(new CsvFileRow("싱글톤", "전역 변수"));
        questionList.add(new CsvFileRow("프록시", "대리 객체"));

        // finderRepository.save(questionList);

        // List<Question> list = questionRepository.findAll();
        // System.out.println("findAll list \n " + list);
    }

}
