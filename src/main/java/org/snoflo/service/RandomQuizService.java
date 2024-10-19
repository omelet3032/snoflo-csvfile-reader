package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.CsvFileRow;

public interface RandomQuizService {

    List<CsvFileRow> findAllQuestion(String selectedFile);

    List<String> findQuestionTable();

}
