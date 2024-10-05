package org.snoflo.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.snoflo.dto.QuestionDto;
import org.snoflo.dto.RandomQuestionDto;

public class Question {

	private String concept;

	private String description;

	public Question() {
	}

	public Question(String concept, String description) {
		this.concept = concept;
		this.description = description;
	}

	public QuestionDto toDto(Question question) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concept == null) ? 0 : concept.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (concept == null) {
			if (other.concept != null)
				return false;
		} else if (!concept.equals(other.concept))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}
