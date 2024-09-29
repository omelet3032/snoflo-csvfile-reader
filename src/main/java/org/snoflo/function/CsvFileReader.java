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
			String quote = "\"";

			StringBuilder descriptionBuilder = new StringBuilder();

			while ((line = reader.readLine()) != null) {

				String[] values = line.split(",");

				descriptionBuilder.setLength(0);

				Question question = new Question();

				if (line.contains(quote)) {

					if (isLastQuoteIndex(line)) {
						question = list.getLast();

						String lastRowDescription = question.getDescription();

						descriptionBuilder = createDescriptionBuilder(lastRowDescription, descriptionBuilder, values);

						// descriptionBuilder.append(description).append(", ");

						// for (int i = 0; i < values.length; i++) {
						// 	if (values[i].contains("\"")) {
						// 		values[i] = values[i].replaceAll(quote, "");
						// 	}
						// 	descriptionBuilder.append(values[i]);
						// 	if (i != values.length - 1) {
						// 		descriptionBuilder.append(",");
						// 	}
						// }

						question.setDescription(descriptionBuilder.toString());

						list.set(list.size() - 1, question);

						continue;
					}

					descriptionBuilder = createDescriptionBuilder(descriptionBuilder, values);
					// for (int i = 1; i < values.length; i++) {
					// 	if (values[i].contains("\"")) {
					// 		values[i] = values[i].replaceAll(quote, "");
					// 	}
					// 	descriptionBuilder.append(values[i]);
					// 	if (i != values.length - 1) {
					// 		descriptionBuilder.append(",");
					// 	}
					// }

					String concept = values[0];
					String description = descriptionBuilder.toString();

					question.setQuestion(concept, description);

					list.add(question);
					continue;
				}

				if (!line.contains(quote)) {
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

	private boolean isLastQuoteIndex(String line) {

		int quoteIndex = line.indexOf("\"");
		int lastLineIndex = line.length() - 1;

		return quoteIndex == lastLineIndex ? true : false;
	}

	private StringBuilder createDescriptionBuilder(String lastRowDescription, StringBuilder descriptionBuilder, String[] values) {

		int includeFirstIndex = 0;
		
		descriptionBuilder.append(lastRowDescription).append(", ");
		
		for (int i = includeFirstIndex; i < values.length; i++) {

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

	private StringBuilder createDescriptionBuilder(StringBuilder descriptionBuilder, String[] values) {

		// StringBuilder descriptionBuilder = new StringBuilder();

			int ignoreFirstIndex = 1;

			for (int i = ignoreFirstIndex; i < values.length; i++) {

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