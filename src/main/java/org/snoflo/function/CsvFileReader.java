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

		// boolean ignoreConceptColumn = true;

		try (BufferedReader reader = Files.newBufferedReader(selectedFile)) {

			// 첫 줄 제거
			String line = reader.readLine();

			boolean isContainQuote = true;

			while ((line = reader.readLine()) != null) {

				String[] values = line.split(",");

				Question question = new Question();
				StringBuilder descriptionBuilder = new StringBuilder();

				if (isContainQuote) {

					if (isQuoteMiddleIndex(line)) {

						descriptionBuilder = createDescriptionBuilder(true, descriptionBuilder, values);

						String concept = values[0];
						String description = descriptionBuilder.toString();

						question.setQuestion(concept, description);

						list.add(question);
						continue;

					} else {

						question = list.getLast();
						int lastIndex = list.size() - 1;

						String beforeDescription = question.getDescription();

						descriptionBuilder = createDescriptionBuilder(false, beforeDescription, descriptionBuilder, values);

						String afterDescription = descriptionBuilder.toString();

						question.setDescription(afterDescription);

						list.set(lastIndex, question);

						continue;
					}

				}

				if (!isContainQuote) {
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
		int lastLineIndex = line.length() - 1;

		return quoteIndex != lastLineIndex ? true : false;
	}

	private StringBuilder createDescriptionBuilder(
			boolean ignoreFirstColumn,
			String beforeDescription,
			StringBuilder descriptionBuilder,
			String[] values) {

		descriptionBuilder.append(beforeDescription).append(", ");

		for (int i = 0; i < values.length; i++) {

			if (values[i].contains("\"")) {
				values[i] = values[i].replaceAll("\"", "");
			}

			descriptionBuilder.append(values[i]);

			if (i != values.length - 1) {
				descriptionBuilder.append(",");
			}

		}

		return descriptionBuilder;
	}

	private StringBuilder createDescriptionBuilder(
			boolean ignoreFirstColumn,
			StringBuilder descriptionBuilder,
			String[] values) {

		for (int i = 1; i < values.length; i++) {

			if (values[i].contains("\"")) {
				values[i] = values[i].replaceAll("\"", "");
			}

			descriptionBuilder.append(values[i]);

			if (i != values.length - 1) {
				descriptionBuilder.append(",");
			}

		}

		return descriptionBuilder;
	}
}