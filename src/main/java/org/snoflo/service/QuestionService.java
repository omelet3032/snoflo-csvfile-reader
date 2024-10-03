package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.Question;

public interface QuestionService {

    Question findConceptById(int id);

    List<Question> findAllQuestion();

}
