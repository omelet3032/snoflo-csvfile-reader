package org.snoflo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snoflo.domain.Row;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class QuestionRepositoryTests {

    private RandomQuizRepositoryImpl questionRepository;
    private HikariDataSource dataSource;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void TestfindAll() {

        // List<Question> list = questionRepository.findAll();


    }

}
