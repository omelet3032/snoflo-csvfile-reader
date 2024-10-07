package org.snoflo.service;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.snoflo.domain.Question;
import org.snoflo.repository.FinderRepositoryImpl;

public class FinderService {

	private FinderRepositoryImpl finderRepository;

	public FinderService(FinderRepositoryImpl finderRepository) {
		this.finderRepository = finderRepository;
	}

	public void saveQuestionList(List<Question> csvRowList, Path selectedFilePath) {
		// boolean isTableCreated = false;

		// while (true) {
		// 	try {
		// 		System.out.println("createTable 실행?");
		// 		finderRepository.createTable(selectedFilePath);
		// 		// isTableCreated = true;
		// 		if (isTableCreated) {
		// 			break;
		// 		}
		// 	} catch (JdbcSQLSyntaxErrorException e) {
		// 		System.out.println("droptable 실행?");
		// 		finderRepository.dropTable(selectedFilePath);
		// 		isTableCreated = true;
		// 		e.printStackTrace();
		// 	} catch (SQLException e) {
		// 		System.out.println("sqlexception?");
		// 		e.printStackTrace();
		// 	}
		// }

		finderRepository.save(csvRowList, selectedFilePath);
	}

	public void createQuestionTable(Path selectedFilePath) {
		try {
			finderRepository.createTable(selectedFilePath);
		} catch (JdbcSQLSyntaxErrorException e) {
			System.out.println("droptable 실행?");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dropQuestionTable(Path selectedFilePath) {
		finderRepository.dropTable(selectedFilePath);
	}

	public void truncateQuestionTable(Path selectedFilePath) {
		finderRepository.truncateTable(selectedFilePath);
	}

	public String checkQuestionTable(Path selectedFilePath) {
		String fileName = selectedFilePath.getFileName().toString();
		fileName = fileName.replace(".csv", "");
		fileName = fileName.toLowerCase();

		List<String> tableList = finderRepository.getTableList();

		for (int i = 0; i < tableList.size(); i++) {
			if (tableList.get(i).equalsIgnoreCase(fileName)) {
				return fileName;
			}
		}

		return "";

	}
}