package org.snoflo.repository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.snoflo.domain.Question;

public class QuestionDataConverter {

	private Question question;

	private List<Question> questionList;

	private CsvFileReader csvFileReader;

	public QuestionDataConverter(CsvFileReader csvFileReader) throws IOException {
		this.csvFileReader = csvFileReader;
	}
	
	public List<Question> convertDataForDomain (Path selectedFile) {
		List<String[]> rowList = this.csvFileReader.readCsvFile(selectedFile); // 추후 수정
		this.questionList = this.convertData(rowList);
		return this.questionList;
	}

	private List<Question> convertData(List<String[]> rowList) {
		this.questionList = rowList.stream()
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

		return this.questionList;
	}

	public List<Question> getQuestionList() {
		return this.questionList;
	}

}