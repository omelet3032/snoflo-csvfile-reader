package org.snoflo.service;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.snoflo.domain.Question;
import org.snoflo.repository.FinderRepository;

public class FinderService {

    private FinderRepository finderRepository;

    public FinderService (FinderRepository finderRepository) {
        this.finderRepository = finderRepository;
    }

    public void saveCsvFile(List<String[]> csvRowList) {
        
        // finderRepository.save(List<Question> questionList);
    };

    public List<Question> convertToQuestionList(List<String[]> rowList) {
		List<Question> questionList = rowList.stream()
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

		return questionList;
	}
}