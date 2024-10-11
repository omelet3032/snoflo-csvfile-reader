package org.snoflo.service;

import java.util.List;

import org.snoflo.domain.Question;

public interface FinderService extends AppService {

	public void saveQuestionList(List<Question> csvRowList, String fileName);

	public void createQuestionTable(String fileName);

	public void truncateQuestionTable(String fileName);

	public String findRegisteredTable(String fileName);

}
