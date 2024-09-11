package org.snoflo.service;

import java.util.List;

import org.snoflo.dto.CsvFileDto;
import org.snoflo.model.Question;
import org.snoflo.repository.DataConverter;
import org.snoflo.repository.QuestionCsvFileReader;
import org.snoflo.repository.QuestionDataConverter;

public class QuestionServiceImpl implements QuestionService {

    private DataConverter<Question> dataConverter;
    private CsvFileDto csvFileDto;
    
    public QuestionServiceImpl (CsvFileDto csvFileDto) {
        this.dataConverter = new QuestionDataConverter(new QuestionCsvFileReader(), csvFileDto);
        this.csvFileDto = csvFileDto;
    }

    // 추후 옵서녈 도입
	@Override
	public Question findConceptById(int id) {
        List<Question> list = dataConverter.getData();

        for (Question concept : list) {
            if (concept.getId() == id) {
                return concept;
            }
        }
        return null;
	}

}
