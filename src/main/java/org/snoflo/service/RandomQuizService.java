package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.Row;

public interface RandomQuizService {

    List<Row> findAllQuestion(String selectedFile);

    List<String> findQuestionTable();

}
