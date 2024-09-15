package org.snoflo.domain;

public class Question {

    int id;

    String concept;

    String description;

    public Question() {
    }

    public Question(int id, String concept, String description) {
        this.id = id;
        this.concept = concept;
        this.description = description;
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
