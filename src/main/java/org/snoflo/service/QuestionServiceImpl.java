package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.Question;
import org.snoflo.repository.QuestionRepository;

public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // 추후 옵서녈 도입
    @Override
    public Question findConceptById(int id) {
        // return this.questionRepository.findConceptById(id);
        return null;
    }

    @Override
    public List<Question> findAllQuestion() {
        return this.questionRepository.findAll();
        // return null;
    }

}
