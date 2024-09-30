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

			String concept = "";
			String description = "";
			StringBuilder descriptionBuilder = new StringBuilder();

			while ((line = reader.readLine()) != null) {

				String[] values = line.split(",");

				descriptionBuilder.setLength(0);

				if (line.contains("\"")) {

					if (isQuoteMiddleIndex(line)) {

						concept = values[0];
						description = appendDescriptionColumn(descriptionBuilder, values);

						addQuestionList(concept, description);

					} else {
						
						Question question = list.getLast();

						description = appendDescriptionColumn(question, descriptionBuilder, values);

						question.setDescription(description);

						this.list.set(list.size() - 1, question);

					}

				} else {
					concept = values[0];
					description = values[1];

					addQuestionList(concept, description);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

		return this.list;
	}

	private void addQuestionList(String concept, String description) {
		Question question = new Question(concept, description);
		this.list.add(question);
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

		int startIndex = (descriptionBuilder.length() == 0) ? 1 : 0;

		for (int i = startIndex; i < values.length; i++) {

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