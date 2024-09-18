package org.snoflo.domain;

import java.util.List;

public class Question {

    int id;

    String concept;

    String description;

	List<Question> questionList;

    public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

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
