package org.snoflo.service;

import java.sql.SQLException;
import java.util.List;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.snoflo.domain.Question;
import org.snoflo.repository.FinderRepository;

public class FinderServiceImpl implements FinderService {

	private FinderRepository finderRepository;

	public FinderServiceImpl(FinderRepository finderRepository) {
		this.finderRepository = finderRepository;
	}

	@Override
	public void saveQuestionList(List<Question> csvRowList, String fileName) {
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

		List<String> tableList = finderRepository.findTableList();

		for (int i = 0; i < tableList.size(); i++) {
			if (tableList.get(i).equalsIgnoreCase(fileName)) {
				return fileName;
			}
		}

		return "";

	}
}