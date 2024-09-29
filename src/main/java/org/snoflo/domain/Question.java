package org.snoflo.domain;

public class Question {

    String concept;

    String description;

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
