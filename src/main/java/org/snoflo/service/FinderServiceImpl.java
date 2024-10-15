package org.snoflo.service;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.snoflo.domain.Question;
import org.snoflo.function.CsvFileParser;
import org.snoflo.repository.FinderRepository;

public class FinderServiceImpl implements FinderService {

	private CsvFileParser csvFileParser;

	private FinderRepository finderRepository;

	public FinderServiceImpl(CsvFileParser csvFileParser, FinderRepository finderRepository) {
		this.csvFileParser = csvFileParser;
		this.finderRepository = finderRepository;
	}

	// @Override
	// public void saveQuestionList(List<Question> csvRowList, String fileName) {
	// finderRepository.save(csvRowList, fileName);
	// }

	@Override
	public void saveQuestionList(Path selectedFile, String fileName) {
		// String fileName = convertPathToString(selectedFile);

		List<Question> csvRowList = csvFileParser.readCsvFile(selectedFile);
		finderRepository.save(csvRowList, fileName);

	}

	@Override
	public void createQuestionTable(String fileName) {
	// public void createQuestionTable(Path selectedFile) {
		// String fileName = convertPathToString(selectedFile);

		try {
			finderRepository.createTable(fileName);
		} catch (JdbcSQLSyntaxErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void truncateQuestionTable(String fileName) {
	// public void truncateQuestionTable(Path selectedFile) {
		// String fileName = convertPathToString(selectedFile);

		finderRepository.truncateTable(fileName);
	}

	@Override
	public String findRegisteredTable(String fileName) {
	// public String findRegisteredTable(Path selectedFile) {

		// String fileName = convertPathToString(selectedFile);

		List<String> tableList = finderRepository.findAllTable();

		for (int i = 0; i < tableList.size(); i++) {
			if (tableList.get(i).equalsIgnoreCase(fileName)) {
				return fileName;
			}
		}

		return "";

	}

	
}