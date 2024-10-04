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

    // private Map<Object, Object> getRandomField(Object obj)
    private Map<Question, Question> getRandomField(Question obj)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

        // Field[] fields = obj.getClass().getDeclaredFields();
        
        // List<Field> filteredFields = new ArrayList<>();
        
        // for (Field field : fields) {
        //     if (field.getName().equals("concept") || field.getName().equals("description")) {
        //         field.setAccessible(true);
        //         filteredFields.add(field);
        //     }
        // }
        
        Field concept = obj.getClass().getDeclaredField("concept");
        concept.setAccessible(true);
        Field description = obj.getClass().getDeclaredField("description");
        description.setAccessible(true);
        
        Field[] filteredFields = new Field[]{concept, description};

        // Map<Object, Object> map = new HashMap<>();
        Map<Question, Question> map = new HashMap<>();

        // int randomIndex = random.nextInt(filteredFields.size());
        int randomIndex = random.nextInt(filteredFields.length);

        // Field selectedField = filteredFields.get(randomIndex); // 필드 이름
        Field selectedField = filteredFields[randomIndex]; // 필드 이름
       
        String fieldName = selectedField.getName();
        System.out.println();

        // Object value = selectedField.get(obj); // 필드의 실제 값
        Question value = (Question) selectedField.get(obj); // 필드의 실제 값


        if (fieldName.equals("concept")) {
            for (Field field : filteredFields) {
                if (field.getName().equals("description")) {
                    // Object descriptionValue = field.get(obj);
                    Question descriptionValue = (Question) field.get(obj);
                    map.put(value, descriptionValue);
                }
            }
        } else if (fieldName.equals("description")) {
            for (Field field : filteredFields) {
                if (field.getName().equals("concept")) {
                    // Object conceptValue = field.get(obj);
                    Question conceptValue = (Question) field.get(obj);
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
            // Map<Object, Object> map = getRandomField(question);
            Map<Question, Question> map = getRandomField(question);

            // for (Map.Entry<Object, Object> entry : map.entrySet()) {
            for (Map.Entry<Question, Question> entry : map.entrySet()) {
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
