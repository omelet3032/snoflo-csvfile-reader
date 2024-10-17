package org.snoflo.repository;

import java.util.List;

import org.snoflo.domain.RandomFields;

public interface QuestionRepository extends AppRepository {

    public List<RandomFields> findAll(String selectedFile);

    public List<String> findAllTable();
}
