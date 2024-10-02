package org.snoflo.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Question {

    private String concept;

    private String description;
	
	private List<Question> questionList;

	public Question() {
	}

	public Question(String concept, String description) {
		this.concept = concept;
		this.description = description;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
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
		return "concept : " + concept + "\ndescription : " + description + "";
	}

}
