package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.RandomFields;
import org.snoflo.repository.QuestionRepository;

public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<RandomFields> findAllQuestion(String selectedFile) {
        return this.questionRepository.findAll(selectedFile);
    }

    @Override
    public List<String> findQuestionTable() {
        return this.questionRepository.findAllTable();
    }

}
