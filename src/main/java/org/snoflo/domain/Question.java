package org.snoflo.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String concept;

    private String description;

	private List<Question> list = new ArrayList<>();
	
	public List<Question> getList() {
		return list;
	}


	public void setList(List<Question> list) {
		this.list = list;
	}


	public Question() {
	}

	
	public Question(String concept) {
		this.concept = concept;
	}


	public Question(String concept, String description) {
		this.concept = concept;
		this.description = description;
	}

	public void setQuestion(String concept, String description) {
		this.concept = concept;
		this.description = description;
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
