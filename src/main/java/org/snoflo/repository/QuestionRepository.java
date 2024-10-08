package org.snoflo.repository;

import java.util.List;

import org.snoflo.domain.Question;

public interface QuestionRepository extends AppRepository {

    public List<Question> findAll(String selectedFile);

    public List<String> findAllTable();
}
