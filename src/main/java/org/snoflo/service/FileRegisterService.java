package org.snoflo.service;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import org.snoflo.domain.Row;

public interface FileRegisterService  {


	public void createQuestionTable(String fileName);

	public void truncateQuestionTable(String fileName);

	public String findRegisteredTable(String fileName);

	public void saveQuestionList(Path selectedFile, String fileName);


}
