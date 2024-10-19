package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.CsvFileRow;
import org.snoflo.repository.RandomQuizRepository;

public class RandomQuizServiceImpl implements RandomQuizService {

    private RandomQuizRepository questionRepository;

    public RandomQuizServiceImpl(RandomQuizRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<CsvFileRow> findAllQuestion(String selectedFile) {
        return this.questionRepository.findAll(selectedFile);
    }

    @Override
    public List<String> findQuestionTable() {
        return this.questionRepository.findAllTable();
    }

}
