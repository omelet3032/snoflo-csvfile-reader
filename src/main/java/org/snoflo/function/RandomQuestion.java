package org.snoflo.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.snoflo.domain.RandomFields;
import org.snoflo.dto.RandomQuestionDto;

public class RandomQuestion {

    private Random random;

    public RandomQuestion() {
        this.random = new Random();
    }
    
    public Map<RandomFields, RandomQuestionDto> getRandomQuestion(List<RandomFields> list) {

        RandomFields question = getRandomElement(list);

        String concept = question.getConcept();
        String description = question.getDescription();

        String[] questionFields = new String[]{concept, description};

        int randomIndex = random.nextInt(questionFields.length);
        int otherIndex = (randomIndex + 1) % questionFields.length;

        String questionValue = questionFields[randomIndex];
        String answerValue = questionFields[otherIndex];

        RandomQuestionDto questionDto = question.toRandomQuestionDto(questionValue, answerValue);

        Map<RandomFields, RandomQuestionDto> map = new HashMap<>();

        map.put(question, questionDto);

        return map;

    }

    private RandomFields getRandomElement(List<RandomFields> list) {
        int randomElement = random.nextInt(list.size());
        RandomFields element = list.get(randomElement);
        return element;
    }

}
