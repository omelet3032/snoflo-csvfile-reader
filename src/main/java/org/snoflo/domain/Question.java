package org.snoflo.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Question {

	int id;

	String concept;

	String description;

	List<Question> questionList;

	public Question() {
	}

	public Question(int id, String concept, String description) {
		this.id = id;
		this.concept = concept;
		this.description = description;
	}


	// // 메서드 
	// public List<Question> addData(List<String[]> rowList) {
	// 	this.questionList = rowList.stream()
	// 			.map(row -> {
	// 				try {
	// 					// 숫자로 변환 가능한지 확인
	// 					int id = Integer.parseInt(row[0]);
	// 					String concept = row[1];
	// 					String description = row[2];

	// 					// 변환 성공하면 Question 객체 반환
	// 					return new Question(id, concept, description);
	// 				} catch (NumberFormatException e) {
	// 					// 변환 실패하면 해당 행 무시
	// 					System.err.println("Invalid number format in row: " + Arrays.toString(row));
	// 					return null; // 잘못된 행을 null로 반환하여 나중에 필터링
	// 				}
	// 			})
	// 			.filter(Objects::nonNull) // null로 반환된 행은 필터링
	// 			.collect(Collectors.toList());

	// 	return this.questionList;
	// }

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "번호 : " + id + "    개념 : " + concept + "    설명 : " + description;
	}

}
