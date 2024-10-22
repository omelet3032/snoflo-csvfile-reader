package org.snoflo.service;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.snoflo.domain.Row;
import org.snoflo.function.FileParser;
import org.snoflo.repository.FileRegisterRepository;
import org.snoflo.repository.TableRepository;

public class FileRegisterServiceImpl implements FileRegisterService {

	private FileParser csvFileParser;

	private FileRegisterRepository finderRepository;
	private TableRepository tableRepository;


	public FileRegisterServiceImpl(FileParser csvFileParser, FileRegisterRepository finderRepository, TableRepository tableRepository) {
		this.csvFileParser = csvFileParser;
		this.finderRepository = finderRepository;
		this.tableRepository = tableRepository;
	}

	@Override
	public void saveQuestionList(Path selectedFile, String fileName) {

		List<Row> csvRowList = csvFileParser.readCsvFile(selectedFile);
		finderRepository.save(csvRowList, fileName);

	}

	@Override
	public void createQuestionTable(String fileName) {

		try {
			// finderRepository.createTable(fileName);
			tableRepository.createTable(fileName);
		} catch (JdbcSQLSyntaxErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void truncateQuestionTable(String fileName) {
		// finderRepository.truncateTable(fileName);
		tableRepository.truncateTable(fileName);
	}

	@Override
	public String findRegisteredTable(String fileName) {

		// List<String> tableList = finderRepository.findAllTable();
		List<String> tableList = tableRepository.findAllTable();

		for (int i = 0; i < tableList.size(); i++) {
			if (tableList.get(i).equalsIgnoreCase(fileName)) {
				return fileName;
			}
		}

		return "";

	}

	
}