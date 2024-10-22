package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.Row;
import org.snoflo.repository.RandomQuizRepository;
import org.snoflo.repository.TableRepository;

public class RandomQuizServiceImpl implements RandomQuizService {

    private RandomQuizRepository questionRepository;
    private TableRepository tableRepository;


    public RandomQuizServiceImpl(RandomQuizRepository questionRepository, TableRepository tableRepository) {
        this.questionRepository = questionRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public List<Row> findAllQuestion(String selectedFile) {
        return this.questionRepository.findAll(selectedFile);
    }

    @Override
    public List<String> findQuestionTable() {
        return this.tableRepository.findAllTable();
    }

}
