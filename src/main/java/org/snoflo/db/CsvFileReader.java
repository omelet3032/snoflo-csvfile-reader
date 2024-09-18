package org.snoflo.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {

	// private static CsvFileReader instance;

	// private CsvFileReader() {
	// }

	// public static CsvFileReader getInstance() {
	// 	if (instance == null) {
	// 		instance = new CsvFileReader();
	// 	}

	// 	return instance;
	// }

	public List<String[]> readCsvFile(Path selectedFile) {

		List<String[]> list = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(selectedFile)) {
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