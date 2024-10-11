package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.Question;

public interface QuestionService extends AppService {

    List<Question> findAllQuestion(String selectedFile);

    List<String> findQuestionTable();

}
