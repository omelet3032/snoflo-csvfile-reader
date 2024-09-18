package org.snoflo.repository;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.db.CsvFileConverter;
import org.snoflo.db.CsvFileManager;
import org.snoflo.domain.Question;

public class QuestionRepository {
    
    private CsvFileConverter dataConverter;


    public QuestionRepository(CsvFileConverter dataConverter) {
        this.dataConverter = dataConverter;
    }

    public void save(Path selectedFile) {
        Question question = new Question();
        question.addData(null);
    }

    // 추후 옵셔널 적용
	public Question findConceptById(int id) {
        List<Question> list = dataConverter.getQuestionList();

        for (Question concept : list) {
            if (concept.getId() == id) {
                return concept;
            }
        }
        return null;
	}

    // public List<Question> findAll() {
    //     List<Question> list = dataConverter.getQuestionList();
    //     return list;
    // }

     public List<Question> findAll() {
        Question question = new Question();
        List<Question> list = question.getQuestionList();
        return list;
    }
    

}
