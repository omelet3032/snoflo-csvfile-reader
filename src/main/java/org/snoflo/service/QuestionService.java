package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.RandomFields;

public interface QuestionService extends AppService {

    List<RandomFields> findAllQuestion(String selectedFile);

    List<String> findQuestionTable();

}
