package org.snoflo.repository;

import java.util.List;

import org.snoflo.domain.Row;

public interface RandomQuizRepository {

    public List<Row> findAll(String selectedFile);

    // public List<String> findAllTable();
}
