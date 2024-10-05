package org.snoflo.function;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.snoflo.domain.Question;
import org.snoflo.dto.RandomQuestionDto;

public class RandomQuestion {

    private Random random = new Random();

    // public Map<Question, List<String>> getRandomQuestion(List<Question> list)
    // throws IllegalArgumentException, IllegalAccessException {
    public Map<Question, RandomQuestionDto> getRandomQuestion(List<Question> list)
            throws IllegalArgumentException, IllegalAccessException {

        /*
         * field를 QuestionDto로 변환해보자
         */
        /*
         * 1. question에서 concept과 description을 추출한다.
         * 2. 추출한 concept과 description을 랜덤으로 돌린다.
         * 3. 랜덤으로 돌린 값을 RandomDto에 넣는다.
         * 이 과정에서 리플렉션이 필요할까?
         * 
         */
        Question question = getRandomElement(list);

        String[] list3 = new String[2];
        list3[0] = question.getConcept();
        list3[1] = question.getDescription();

        int randomIndex = random.nextInt(list3.length);
        int otherIndex = (randomIndex + 1) % list3.length;

        // Field[] fields = Question.class.getDeclaredFields();
        // int randomIndex = random.nextInt(fields.length);

        // fields[randomIndex].setAccessible(true);
        // Object questionField = fields[randomIndex].get(question);

        // int otherIndex = (randomIndex + 1) % fields.length;
        // fields[otherIndex].setAccessible(true);
        // Object answerField = fields[otherIndex].get(question);

        // String questionValue = (String) questionField;
        // String answerValue = (String) answerField;

        String questionValue = list3[randomIndex];
        String answerValue = list3[otherIndex];

        RandomQuestionDto dto = question.toDto(questionValue, answerValue);
        // Map<Question, List<String>> map = new HashMap<>();
        Map<Question, RandomQuestionDto> map = new HashMap<>();
        // List<String> valueList = new ArrayList<>();
        // valueList.add(questionValue);
        // valueList.add(answerValue);

        // map.put(questionValue, answerValue);
        // map.put(question, valueList);
        map.put(question, dto);

        return map;

    }

    private Question getRandomElement(List<Question> list) {
        int randomElement = random.nextInt(list.size());
        Question element = list.get(randomElement);
        return element;
    }

}
