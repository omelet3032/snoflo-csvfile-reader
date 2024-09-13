package org.snoflo.service;

import java.util.List;

import org.snoflo.dto.CsvFileDto;
import org.snoflo.dto.CsvFilesFinder;
import org.snoflo.model.Question;
import org.snoflo.repository.DataConverter;
import org.snoflo.repository.QuestionDataConverter;

public class QuestionServiceImpl implements QuestionService {

    private DataConverter<Question> dataConverter;
    private CsvFilesFinder csvFilesFinder;
    
    private CsvFileDto csvFileDto;

    public QuestionServiceImpl () {
        // this.dataConverter = dataConverter;
        this.csvFilesFinder = new CsvFilesFinder();
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

    // CsvFilesFinder클래스가 제공하는 csv파일리스트 찾기 메서드를 실행하여 그 값을 반환한다.
    @Override
    public List<String> findCsvFiles() {
        return csvFilesFinder.getCsvFileNames();
    }

    @Override
	public void setCsvFileDto(CsvFileDto csvFileDto) {
		this.csvFileDto = csvFileDto;
        this.dataConverter = new QuestionDataConverter(csvFileDto);
	}

}
