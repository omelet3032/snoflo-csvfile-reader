package org.snoflo.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
		this.dataOfCsvFile = csvFileReader.readCsvFile(csvFileDto); // 추후 수정
		convertData();
	}

	// @Override
	// public List<Question> convertData() throws IOException {
	// this.conversionData = dataOfCsvFile.stream().map(row -> new
	// Question(Integer.parseInt(row[0]), row[1], row[2]))
	// .collect(Collectors.toList());
	// return conversionData;
	// }

	@Override
	public List<Question> convertData() throws IOException {
		this.conversionData = dataOfCsvFile.stream()
				.map(row -> {
					try {
						// 숫자로 변환 가능한지 확인
						int id = Integer.parseInt(row[0]);
						String concept = row[1];
						String description = row[2];

						// 변환 성공하면 Question 객체 반환
						return new Question(id, concept, description);
					} catch (NumberFormatException e) {
						// 변환 실패하면 해당 행 무시
						System.err.println("Invalid number format in row: " + Arrays.toString(row));
						return null; // 잘못된 행을 null로 반환하여 나중에 필터링
					}
				})
				.filter(Objects::nonNull) // null로 반환된 행은 필터링
				.collect(Collectors.toList());

		return conversionData;
	}

	@Override
	public List<Question> getData() {
		return this.conversionData;
	}

}