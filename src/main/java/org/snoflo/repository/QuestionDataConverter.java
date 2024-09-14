package org.snoflo.repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.snoflo.dto.CsvFileDto;
import org.snoflo.model.Question;

public class QuestionDataConverter implements DataConverter {

	private List<String[]> dataOfCsvFile;
	private List<Question> conversionData;
	private CsvFileReader csvFileReader;
	// private CsvFileDto csvFileDto;

	// private CsvFilesFinderService csvFilesFinderService;

	public QuestionDataConverter(CsvFileReader csvFileReader, CsvFileDto csvFileDto) throws IOException {
		this.csvFileReader = csvFileReader;
		// this.csvFilesFinderService = csvFilesFinderService;
		// csvFilesFinderService.getCsvFileDto();
		// CsvFileDto csvFileDto = new CsvFileDto(null);
		this.dataOfCsvFile = csvFileReader.readCsvFile(csvFileDto); //  추후 수정
		convertData();
	}

	@Override
	public List<Question> convertData() throws IOException {
		this.conversionData = dataOfCsvFile.stream().map(row -> new Question(Integer.parseInt(row[0]), row[1], row[2]))
				.collect(Collectors.toList());
		return conversionData;
	}

	@Override
	public List<Question> getData() {
		return this.conversionData;
	}

}