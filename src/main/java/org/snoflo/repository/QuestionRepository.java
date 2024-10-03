package org.snoflo.repository;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import org.snoflo.domain.Question;

import com.zaxxer.hikari.HikariDataSource;

public class QuestionRepository {

    private HikariDataSource dataSource;

    public QuestionRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Question> findAll() {

        // Question question = new Question();

        List<Question> list = new ArrayList<>();

        try (Connection conection = dataSource.getConnection()) {

            String sql = """
                    SELECT concept, description
                    FROM question
                    """;

            PreparedStatement statement = conection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                String concept = rs.getString("concept");
                String description = rs.getString("description");
                // System.out.println("rs con des : " + concept + " " + description);
                // Question question = new Question(concept, description);
                // System.out.println("rs에서 반환하는 question \n" + question.toString());

                list.add(new Question(concept,description));
                // list.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println("QR에서 반환한 list \n" + list.toString());
        return list;
    }

    // public void save(Path selectedFile) {
    // Question question = new Question();
    // question.addData(null);
    // }

    // 추후 옵셔널 적용
    // public Question findConceptById(int id) {
    // List<Question> list = csvFileManager.getQuestionList();

    // for (Question concept : list) {
    // if (concept.getId() == id) {
    // return concept;
    // }
    // }
    // return null;
    // }

    // public List<Question> findAll() {
    // List<Question> list = csvFileManager.getQuestionList();
    // return list;
    // }

}
