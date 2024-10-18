package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.CsvFileRow;

public interface QuestionService extends AppService {

    List<CsvFileRow> findAllQuestion(String selectedFile);

    List<String> findQuestionTable();

}
