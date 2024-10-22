package org.snoflo.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.snoflo.domain.Question;
import org.snoflo.domain.Row;
import org.snoflo.dto.QuestionDto;
import org.snoflo.dto.RandomQuestionDto;

public class RandomQuestionGenerator {

    private Random random;

    private Question question;

    public RandomQuestionGenerator() {
        this.random = new Random();
        this.question = new Question();
    }

    public List<QuestionDto> getRandomQuestion2(List<Row> list) {
        List<QuestionDto> questionList = new ArrayList<>();

        while (!list.isEmpty()) {
            Row row = getRandomElement(list);

            String concept = row.getConcept();
            String description = row.getDescription();

            String[] rowFields = new String[] { concept, description };

            int randomIndex = random.nextInt(rowFields.length);
            int otherIndex = (randomIndex + 1) % rowFields.length;

            String questionValue = rowFields[randomIndex];
            String answerValue = rowFields[otherIndex];

            QuestionDto questionDto = question.toDto(questionValue, answerValue);
            questionList.add(questionDto);
            list.remove(row);
        }

        // Row row = getRandomElement(list);

        // String concept = row.getConcept();
        // String description = row.getDescription();

        // String[] rowFields = new String[] { concept, description };

        // int randomIndex = random.nextInt(rowFields.length);
        // int otherIndex = (randomIndex + 1) % rowFields.length;

        // String questionValue = rowFields[randomIndex];
        // String answerValue = rowFields[otherIndex];

        // QuestionDto questionDto = question.toDto(questionValue, answerValue);
        // questionList.add(questionDto);

        return questionList;

    }

    public Map<Row, QuestionDto> getRandomQuestion(List<Row> list) {

        Row row = getRandomElement(list);

        String concept = row.getConcept();
        String description = row.getDescription();

        String[] rowFields = new String[] { concept, description };

        int randomIndex = random.nextInt(rowFields.length);
        int otherIndex = (randomIndex + 1) % rowFields.length;

        String questionValue = rowFields[randomIndex];
        String answerValue = rowFields[otherIndex];

        QuestionDto questionDto = question.toDto(questionValue, answerValue);

        Map<Row, QuestionDto> map = new HashMap<>();

        map.put(row, questionDto);

        return map;

    }

    private Row getRandomElement(List<Row> list) {
        int randomElement = random.nextInt(list.size());
        Row element = list.get(randomElement);
        return element;
    }

}
