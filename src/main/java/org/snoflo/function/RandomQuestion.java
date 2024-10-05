package org.snoflo.function;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.snoflo.domain.Question;
import org.snoflo.dto.RandomQuestionDto;

public class RandomQuestion {

    private Random random = new Random();

    /* 
     * 메서드 책임 분리 필요
     *  현재는 random + dto변환까지 책임이 2개
     */
    public Map<Question, RandomQuestionDto> getRandomQuestion(List<Question> list) {

        Question question = getRandomElement(list);

        String[] questionFields = new String[]{question.getConcept(), question.getDescription()};

        int randomIndex = random.nextInt(questionFields.length);
        int otherIndex = (randomIndex + 1) % questionFields.length;

        String questionValue = questionFields[randomIndex];
        String answerValue = questionFields[otherIndex];

        RandomQuestionDto questionDto = question.toRandomQuestionDto(questionValue, answerValue);
        
        Map<Question, RandomQuestionDto> map = new HashMap<>();
        
        map.put(question, questionDto);

        return map;

    }

    private Question getRandomElement(List<Question> list) {
        int randomElement = random.nextInt(list.size());
        Question element = list.get(randomElement);
        return element;
    }

}
