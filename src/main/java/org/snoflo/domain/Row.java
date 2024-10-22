package org.snoflo.domain;

public class Row {

	private String concept;

	private String description;

	public Row() {
	}

	public Row(String concept, String description) {
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
