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

    public Map<Object, Object> getRandomField(Object obj) throws IllegalArgumentException, IllegalAccessException {

        Field[] fields = obj.getClass().getDeclaredFields();

        Question question = new Question();

        List<Field> filteredFields = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equals("list")) {
                filteredFields.add(field);
            }
        }

        Random random = new Random();
        Map<Object, Object> map = new HashMap<>();

        int randomIndex = random.nextInt(filteredFields.size());

        Field selectedField = filteredFields.get(randomIndex); // 필드 이름
        String fieldName = selectedField.getName();
        System.out.println();

        System.out.println();
        Object value = selectedField.get(obj); // 필드의 실제 값

        if (fieldName.equals("concept")) {
            for (Field field : filteredFields) {
                if (field.getName().equals("description")) {
                    Object descriptionValue = field.get(obj);
                    map.put(value, descriptionValue);
                }
            }
        } else if (fieldName.equals("description")) {
            for (Field field : filteredFields) {
                if (field.getName().equals("concept")) {
                    Object conceptValue = field.get(obj);
                    map.put(value, conceptValue);
                }
            }
        }

        return map;

    }

    private Question getRandomElement(List<Question> list) {
        Random random = new Random();
        int randomElement = random.nextInt(list.size());
        Question element = list.get(randomElement);

        return element;
    }

    private List<Question> removeQuestion(List<Question> list, Question element) {

        list.remove(element);
        return list;
    }

    public void playRandomQuiz(List<Question> list) throws IllegalArgumentException, IllegalAccessException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("퀴즈를 시작합니다.");
        System.out.println();
        scanner.nextLine();
        while (!list.isEmpty()) {

            Question question = getRandomElement(list);
            Map<Object, Object> map = getRandomField(question);
        
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                System.out.println("질문 : " + entry.getKey());
                scanner.nextLine();
                System.out.println("정답 : " + entry.getValue());
                scanner.nextLine();

            }
            
            list = removeQuestion(list, question);

        }

        // return list;
    }

   
}
