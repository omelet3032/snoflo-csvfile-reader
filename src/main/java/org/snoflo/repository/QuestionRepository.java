package org.snoflo.repository;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.domain.Question;

import com.zaxxer.hikari.HikariDataSource;

public class QuestionRepository {
    
    private HikariDataSource dataSource;

    public QuestionRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // public void save(Path selectedFile) {
    //     Question question = new Question();
    //     question.addData(null);
    // }

    // 추후 옵셔널 적용
	// public Question findConceptById(int id) {
    //     List<Question> list = csvFileManager.getQuestionList();

    //     for (Question concept : list) {
    //         if (concept.getId() == id) {
    //             return concept;
    //         }
    //     }
    //     return null;
	// }

    // public List<Question> findAll() {
    //     List<Question> list = dataConverter.getQuestionList();
    //     return list;
    // }

    //  public List<Question> findAll() {
    //     List<Question> list = csvFileManager.getQuestionList();
    //     return list;
    // }
    

}
