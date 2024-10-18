package org.snoflo.service;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.domain.CsvFileRow;

public interface FinderService extends AppService {

	// public void saveQuestionList(List<Question> csvRowList, String fileName);

	public void createQuestionTable(String fileName);

	public void truncateQuestionTable(String fileName);

	public String findRegisteredTable(String fileName);

	public void saveQuestionList(Path selectedFile, String fileName);

	// public void createQuestionTable(Path selectedFile);

	// public void truncateQuestionTable(Path selectedFile);

	// public String findRegisteredTable(Path selectedFile);

}
