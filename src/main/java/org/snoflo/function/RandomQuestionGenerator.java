package org.snoflo.function;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Collections;

import org.snoflo.domain.Question;
import org.snoflo.domain.Row;

public class RandomQuestionGenerator {

    private Random random;

    public RandomQuestionGenerator() {
        this.random = new Random();
    }

    public List<Question> getRandomQuestion(List<Row> list) {
        List<Question> questionList = new ArrayList<>();

        Iterator<Row> iterator = getRandomElement(list);

        while (iterator.hasNext()) {
            Row row = iterator.next();

            String concept = row.getConcept();
            String description = row.getDescription();

            String[] rowFields = new String[] { concept, description };

            int randomIndex = random.nextInt(rowFields.length);
            int otherIndex = (randomIndex + 1) % rowFields.length;

            String questionValue = rowFields[randomIndex];
            String answerValue = rowFields[otherIndex];

            Question question = new Question();
            
            question.convertRowToQuestion(questionValue, answerValue);
            questionList.add(question);
            iterator.remove();
        }

        return questionList;

    }

    private Iterator<Row> getRandomElement(List<Row> list) {

        Collections.shuffle(list);

        Iterator<Row> iterator = list.iterator();
        return iterator;
    }

}
