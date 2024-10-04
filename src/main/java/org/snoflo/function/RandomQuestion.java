package org.snoflo.function;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.snoflo.domain.Question;

//cherry pick
public class RandomQuestion {

    private Random random;

    public RandomQuestion() {
        this.random = new Random();
    }

    private Map<String, String> getRandomField(Question obj)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

        Field concept = obj.getClass().getDeclaredField("concept");
        concept.setAccessible(true);
        Field description = obj.getClass().getDeclaredField("description");
        description.setAccessible(true);
        
        Field[] filteredFields = new Field[]{concept, description};

        Map<String, String> map = new HashMap<>();

        int randomIndex = random.nextInt(filteredFields.length);

        Field selectedField = filteredFields[randomIndex]; // 필드 이름
       
        String fieldName = selectedField.getName();
        System.out.println();

        Object value1 = selectedField.get(obj); // 필드의 실제 값
        String value = null;
        if (value1 instanceof String ) {
            value = (String) value1;
        }

        if (fieldName.equals("concept")) {
            for (Field field : filteredFields) {
                if (field.getName().equals("description")) {
                    String descriptionValue = (String) field.get(obj);
                    map.put(value, descriptionValue);
                }
            }
        } else if (fieldName.equals("description")) {
            for (Field field : filteredFields) {
                if (field.getName().equals("concept")) {
                    String conceptValue = (String) field.get(obj);
                    map.put(value, conceptValue);
                }
            }
        }

        return map;

    }

    private Question getRandomElement(List<Question> list) {
        int randomElement = random.nextInt(list.size());
        Question element = list.get(randomElement);

        return element;
    }

    public void playRandomQuiz(List<Question> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("퀴즈를 시작합니다.");
        System.out.println();
        scanner.nextLine();

        while (!list.isEmpty()) {

            Question question = getRandomElement(list);
            Map<String, String> map = getRandomField(question);

            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println("질문 : " + entry.getKey());
                scanner.nextLine();
                System.out.println("정답 : " + entry.getValue());
                scanner.nextLine();

            }

            list.remove(question);

        }

        System.out.println("퀴즈를 종료합니다.");
        scanner.close();
    }

}
