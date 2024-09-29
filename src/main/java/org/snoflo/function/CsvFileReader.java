package org.snoflo.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.snoflo.domain.Question;

public class CsvFileReader {

	private List<Question> list = new ArrayList<>();

	public List<Question> readCsvFile(Path selectedFile) {

		try (BufferedReader reader = Files.newBufferedReader(selectedFile)) {

			// 첫 줄 제거
			String line = reader.readLine();

			StringBuilder descriptionBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null) {

				boolean isContainQuote = line.contains("\"");
				String[] values = line.split(",");

				Question question = new Question();
				descriptionBuilder.setLength(0);

				if (isContainQuote) {

					if (isQuoteMiddleIndex(line)) {

						String concept = values[0];
						String description = appendDescriptionColumn(descriptionBuilder, values);

						question.setQuestion(concept, description);
						list.add(question);
						continue;

					} else {
						question = list.getLast();

						String description = appendDescriptionColumn(question, descriptionBuilder, values);

						question.setDescription(description);

						list.set(list.size() - 1, question);

						continue;
					}

				} else {
					String concept = values[0];
					String description = values[1];

					question.setQuestion(concept, description);
					list.add(question);
					continue;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

		return this.list;
	}

	private boolean isQuoteMiddleIndex(String line) {

		int quoteIndex = line.indexOf("\"");
		int lastIndex = line.length() - 1;

		return quoteIndex != lastIndex;
	}

	private String appendDescriptionColumn(
			Question question,
			StringBuilder descriptionBuilder,
			String[] values) {

		String beforeDescription = question.getDescription();
		descriptionBuilder = descriptionBuilder.append(beforeDescription).append(", ");

		return appendDescriptionColumn(descriptionBuilder, values);
	}

	private String appendDescriptionColumn(
			StringBuilder descriptionBuilder,
			String[] values) {

		boolean startIndex = (descriptionBuilder.length() == 0);

		for (int i = startIndex ? 1 : 0; i < values.length; i++) {

			if (values[i].contains("\"")) {
				values[i] = values[i].replaceAll("\"", "");
			}

			descriptionBuilder.append(values[i]);

			if (i != values.length - 1) {
				descriptionBuilder.append(",");
			}

		}

		return descriptionBuilder.toString();

	}
}