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
			
			String[] values;
			String quote = "\"";
			
			StringBuilder conceptBuilder;
			StringBuilder descriptionBuilder;
			
			Question question = new Question();

			while ((line = reader.readLine()) != null) {
				int quoteIndex = line.indexOf("\"");
				int lastQuoteIndex = line.lastIndexOf("\"");

				conceptBuilder = new StringBuilder();
				descriptionBuilder = new StringBuilder();

				if (!line.contains(quote)) {
					System.out.println("1번 조건문에 걸림");
					values = line.split(",");

					question = new Question();

					conceptBuilder.append(values[0]);
					descriptionBuilder.append(values[1]);

					question.setConcept(conceptBuilder.toString());
					question.setDescription(descriptionBuilder.toString());

					list.add(question);

					System.out.println();
					System.out.println("  1번 조건문의 question : " + question.toString());
					System.out.println();
					System.out.println("  현재 list " + list);
					System.out.println();
					System.out.println("-------------");
					continue;
				}

				if (line.contains(quote)) {
					System.out.println("2번 조건문 진입");
					System.out.println("line : " + line);
					System.out.println();

					if (quoteIndex != lastQuoteIndex) {
						System.out.println("2-3번 조건문");
						System.out.println("quoteIndex : " + quoteIndex);
						System.out.println("line의 길이 : " + line.length());
						System.out.println();

						line = line.replaceAll(quote, "");
						values = line.split(",");

						question = new Question();

						conceptBuilder.append(values[0]);
						question.setConcept(conceptBuilder.toString());

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

					if (quoteIndex != 0 && quoteIndex != line.length() - 1) {
						System.out.println("2-1번 조건문");
						System.out.println("line의 길이 : " + line.length());
						System.out.println("quoteIndex : " + quoteIndex);
						System.out.println();

						line = line.replaceAll(quote, "");
						values = line.split(",");

						question = new Question();
						conceptBuilder.append(values[0]);
						question.setConcept(conceptBuilder.toString());

						if (values.length == 2) {
							System.out.println("2-1-1번 조건문");
							System.out.println();
							descriptionBuilder.append(values[1]);
							question.setDescription(descriptionBuilder.toString());
						} else if (values.length > 2) {
							System.out.println("2-1-2번 조건문");
							System.out.println();
							for (int i = 1; i < values.length; i++) {
								descriptionBuilder.append(values[i]);
								if (i != values.length - 1) {
									descriptionBuilder.append(",");
								}
							}

							question.setDescription(descriptionBuilder.toString());
						}

						System.out.println("  2번 조건문의 question : " + question.toString());
						System.out.println();
						System.out.println("  현재 list " + list);
						System.out.println();

						continue;

					} else if (quoteIndex == line.length() - 1) {
						System.out.println("2-2번 조건문");
						System.out.println("quoteIndex : " + quoteIndex);
						System.out.println("line의 길이 : " + line.length());
						System.out.println();
						line = line.replaceAll(quote, "");

						String description = question.getDescription();
						descriptionBuilder.append(description).append(", ").append(line);

						question.setDescription(descriptionBuilder.toString());
						list.add(question);

						System.out.println("  현재 list " + list);
						continue;
					}

				}

			}

		} catch (

		IOException e) {
			e.printStackTrace();

		}
		return this.list;
	}

}