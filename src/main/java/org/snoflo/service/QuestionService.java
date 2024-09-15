package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.Question;
import org.snoflo.dto.FileDto;

public interface QuestionService {

    Question findConceptById(int id);

    List<Question> findAll();

    // void start(FileDto fileDto);

}
