package org.snoflo.function;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.snoflo.domain.Question;

public class RandomQuestion {

    private Random random = new Random();

    // public Map<String, String> getRandomQuestion(List<Question> list) throws IllegalArgumentException, IllegalAccessException {
    public Map<Question, List<String>> getRandomQuestion(List<Question> list) throws IllegalArgumentException, IllegalAccessException {

       /* field를 QuestionDto로 변환해보자
        */ 
            Question question = getRandomElement(list);

            Field[] fields = Question.class.getDeclaredFields();
            int randomIndex = random.nextInt(fields.length);

            fields[randomIndex].setAccessible(true);
            Object questionField = fields[randomIndex].get(question);

            int otherIndex = (randomIndex + 1) % fields.length;
            fields[otherIndex].setAccessible(true);
            Object answerField = fields[otherIndex].get(question);

            String questionValue = (String) questionField;
            String answerValue = (String) answerField;

            // Map<String, String> map = new HashMap<>();
            Map<Question, List<String>> map = new HashMap<>();

            List<String> valueList = new ArrayList<>();
            valueList.add(questionValue);
            valueList.add(answerValue);
            // map.put(questionValue, answerValue);
            map.put(question, valueList);

        return map;

    }

    private Question getRandomElement(List<Question> list) {
        int randomElement = random.nextInt(list.size());
        Question element = list.get(randomElement);
        // list.remove(element);
        return element;
    }

}
