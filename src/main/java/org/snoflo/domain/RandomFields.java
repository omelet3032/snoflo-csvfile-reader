package org.snoflo.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.snoflo.dto.QuestionDto;
import org.snoflo.dto.RandomQuestionDto;

public class RandomFields {

	private String concept;

	private String description;

	public RandomFields() {
	}

	public RandomFields(String concept, String description) {
		this.concept = concept;
		this.description = description;
	}

	public QuestionDto toDto(RandomFields question) {
		return new QuestionDto(question.getConcept(), question.getDescription());
	}

	public RandomQuestionDto toRandomQuestionDto(String question, String answer) {
		return new RandomQuestionDto(question, answer);
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