package org.snoflo.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.snoflo.domain.Question;
import org.snoflo.repository.FinderRepositoryImpl;
import org.snoflo.repository.FinderRepository;

public class FinderService {

	// private FinderRepositoryImpl finderRepository;
	private FinderRepository finderRepository;

	public FinderService(FinderRepository finderRepository) {
		this.finderRepository = finderRepository;
	}

	public void saveQuestionList(List<Question> csvRowList, String fileName) {
		finderRepository.save(csvRowList, fileName);
	}

	public void createQuestionTable(String fileName) {
		try {
			finderRepository.createTable(fileName);
		} catch (JdbcSQLSyntaxErrorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// public void dropQuestionTable(String fileName) {
	// 	finderRepository.dropTable(fileName);
	// }

	public void truncateQuestionTable(String fileName) {
		finderRepository.truncateTable(fileName);
	}

	public String getQuestionTable(String fileName) {

		List<String> tableList = finderRepository.getTableList();

		for (int i = 0; i < tableList.size(); i++) {
			if (tableList.get(i).equalsIgnoreCase(fileName)) {
				return fileName;
			}
		}

		return "";

	}
}