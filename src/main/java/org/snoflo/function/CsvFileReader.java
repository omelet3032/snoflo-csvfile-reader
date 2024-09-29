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

			// Question question = null;

			StringBuilder descriptionBuilder = new StringBuilder();

			// String[] values;

			// String concept;
			// String description;

			while ((line = reader.readLine()) != null) {

				int firstQuoteIndex = line.indexOf("\"");
				int lastQuoteIndex = line.lastIndexOf("\"");

				Question question = new Question();
				String[] values = line.split(",");

				descriptionBuilder.setLength(0);

				if (!line.contains(quote)) {
					// String[] values = line.split(",");

					String concept = values[0];
					String description = values[1];

					question.setConcept(concept);
					question.setDescription(description);

					list.add(question);

					continue;
				}

				if (line.contains(quote)) {

					// line에 따옴표(quote)가 2개 있는 경우
					if (firstQuoteIndex != lastQuoteIndex) {
						line = line.replaceAll(quote, "");
						// String[] values = line.split(",");

						String concept = values[0];
						question.setConcept(concept);

						for (int i = 1; i < values.length; i++) {
							if (values[i].contains("\"")) {
								values[i] = values[i].replaceAll(quote, "");
							}
							descriptionBuilder.append(values[i]);
							if (i != values.length - 1) {
								descriptionBuilder.append(",");
							}
						}

						question.setDescription(descriptionBuilder.toString());

						list.add(question);
						continue;
					}
					// 따옴표(quote)가 중간에 있는 경우
					if (firstQuoteIndex != 0 && firstQuoteIndex != line.length() - 1) {
						line = line.replaceAll(quote, "");
						// String[] values = line.split(",");
						// if (values[i].contains("\"")) {
						// values[i] = values[i].replaceAll(quote, "");
						// }

						question = new Question();
						String concept = values[0];
						question.setConcept(concept);

						// StringBuilder descriptionBuilder = new StringBuilder();

						if (values.length == 2) {
							String description = values[1].replaceAll(quote, "");
							// descriptionBuilder.append(description);
							question.setDescription(description);
						} else if (values.length > 2) {
							for (int i = 1; i < values.length; i++) {
								descriptionBuilder.append(values[i]);
								if (i != values.length - 1) {
									descriptionBuilder.append(",");
								}
							}
							question.setDescription(descriptionBuilder.toString());
						}
						// 수정 코드
						list.add(question);
						continue;

						// 따옴표(quote)가 마지막에 있는 경우
					} else if (firstQuoteIndex == line.length() - 1) {
						line = line.replaceAll(quote, "");
						// String[] values = line.split(",");

						question = list.getLast();

						String description = question.getDescription();

						descriptionBuilder.append(description).append(", ");

						for (int i = 0; i < values.length; i++) {
							if (values[i].contains("\"")) {
								values[i] = values[i].replaceAll(quote, "");
							}
							descriptionBuilder.append(values[i]);
							if (i != values.length - 1) {
								descriptionBuilder.append(",");
							}
						}

						question.setDescription(descriptionBuilder.toString());

						list.set(list.size() - 1, question);

						continue;

					}

				}

			}

		} catch (IOException e) {
			e.printStackTrace();

		}
		return this.list;
	}

}