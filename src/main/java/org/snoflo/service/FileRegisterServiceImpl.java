package org.snoflo.service;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.snoflo.domain.CsvFileRow;
import org.snoflo.function.CsvFileParser;
import org.snoflo.repository.FileRegisterRepository;

public class FileRegisterServiceImpl implements FileRegisterService {

	private CsvFileParser csvFileParser;

	private FileRegisterRepository finderRepository;

	public FileRegisterServiceImpl(CsvFileParser csvFileParser, FileRegisterRepository finderRepository) {
		this.csvFileParser = csvFileParser;
		this.finderRepository = finderRepository;
	}

	@Override
	public void saveQuestionList(Path selectedFile, String fileName) {

		List<CsvFileRow> csvRowList = csvFileParser.readCsvFile(selectedFile);
		finderRepository.save(csvRowList, fileName);

	}

	@Override
	public void createQuestionTable(String fileName) {

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
		finderRepository.truncateTable(fileName);
	}

	@Override
	public String findRegisteredTable(String fileName) {

		List<String> tableList = finderRepository.findAllTable();

		for (int i = 0; i < tableList.size(); i++) {
			if (tableList.get(i).equalsIgnoreCase(fileName)) {
				return fileName;
			}
		}

		return "";

	}

	
}