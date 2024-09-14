package org.snoflo.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.snoflo.dto.CsvFileDto;

public class QuestionCsvFileReader implements CsvFileReader {

	@Override
	public List<String[]> readCsvFile(CsvFileDto csvFileDto) {

		Path filePath = csvFileDto.fileName();
		
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