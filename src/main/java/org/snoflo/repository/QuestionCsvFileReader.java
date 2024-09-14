package org.snoflo.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class QuestionCsvFileReader implements CsvFileReader {

	@Override
	public List<String[]> readCsvFile(String csvFile) {

		String folderName = "csv";
		Path dirPath = Paths.get(System.getProperty("user.dir"), folderName);
		Path filePath = dirPath.resolve(csvFile);

		List<String[]> list = new ArrayList<>();
		
		try (BufferedReader reader = Files.newBufferedReader(filePath)) {
			String line;
			reader.readLine(); // 첫 줄 없애기

			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				list.add(values);
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

		return list;
	}

}