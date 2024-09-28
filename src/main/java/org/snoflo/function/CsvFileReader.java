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

			String line;
			line = reader.readLine();

			String quote = "\"";

			// StringBuilder conceptBuilder;
			// StringBuilder descriptionBuilder;

			Question question = null;

			while ((line = reader.readLine()) != null) {

				int quoteIndex = line.indexOf("\"");
				int lastQuoteIndex = line.lastIndexOf("\"");

				// conceptBuilder = new StringBuilder();
				// descriptionBuilder = new StringBuilder();

				if (!line.contains(quote)) {
					String[] values = line.split(",");

					question = new Question(values[0], values[1]);

					list.add(question);

					continue;
				}

				if (line.contains(quote)) {

					// line에 따옴표(quote)가 2개 있는 경우
					if (quoteIndex != lastQuoteIndex) {
						line = line.replaceAll(quote, "");
						String[] values = line.split(",");
						// 프록시(Proxy),"실제 객체에 대한 대리 객체를 사용 → 메모리 용량↓, 정보은닉"

						question = new Question();
						question.setConcept(values[0]);

						StringBuilder descriptionBuilder = new StringBuilder();

						for (int i = 1; i < values.length; i++) {
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
					if (quoteIndex != 0 && quoteIndex != line.length() - 1) {
						line = line.replaceAll(quote, "");
						String[] values = line.split(",");

						question = new Question();
						question.setConcept(values[0]);
						StringBuilder descriptionBuilder = new StringBuilder();

						if (values.length == 2) {
							descriptionBuilder.append(values[1]);
							question.setDescription(descriptionBuilder.toString());
						} else if (values.length > 2) {
							for (int i = 1; i < values.length; i++) {
								descriptionBuilder.append(values[i]);
								if (i != values.length - 1) {
									descriptionBuilder.append(",");
								}
							}
							question.setDescription(descriptionBuilder.toString());
						}
						continue;

					// 따옴표(quote)가 마지막에 있는 경우
					} else if (quoteIndex == line.length() - 1) {
						line = line.replaceAll(quote, "");

						String description = question.getDescription();

						StringBuilder descriptionBuilder = new StringBuilder()
								.append(description)
								.append(", ")
								.append(line);

						question.setDescription(descriptionBuilder.toString());
						list.add(question);

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