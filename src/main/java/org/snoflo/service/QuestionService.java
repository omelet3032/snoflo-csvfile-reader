package org.snoflo.service;

import java.util.List;

import org.snoflo.dto.CsvFileDto;
import org.snoflo.model.Question;

public interface QuestionService {

    Question findConceptById(int id);

    List<String> findCsvFiles();

    void setCsvFileDto(CsvFileDto csvFileDto);

}
