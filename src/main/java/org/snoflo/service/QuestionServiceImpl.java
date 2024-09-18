package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.Question;
import org.snoflo.repository.QuestionDataConverter;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDataConverter dataConverter;

    public QuestionServiceImpl (QuestionDataConverter dataConverter) {
        this.dataConverter = dataConverter;
    }

    
    // @Override
	// public void start(FileDto fileDto) {
    //     dataConverter.processDto(fileDto);
	// }


	// 추후 옵서녈 도입
	@Override
	public Question findConceptById(int id) {
        List<Question> list = dataConverter.getQuestionList();

        for (Question concept : list) {
            if (concept.getId() == id) {
                return concept;
            }
        }
        return null;
	}

    @Override
    public List<Question> findAll() {
        List<Question> list = dataConverter.getQuestionList();
        return list;
    }


    
    

}
