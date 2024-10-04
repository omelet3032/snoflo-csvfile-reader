package org.snoflo.function;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.snoflo.domain.Question;

//cherry pick
public class RandomQuestion {

    private Random random;

    private Map<String, String> map;

    public RandomQuestion() {
        this.random = new Random();
        this.map = new HashMap<>();
    }

    private Map<String, String> getRandomField(Question obj)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

        Field[] fields = obj.getClass().getDeclaredFields();

        Field[] filteredFields = new Field[2];

        for (Field field : fields) {
            if (field.getName().equals("concept")) {
                filteredFields[0] = field;
            } else if (field.getName().equals("description")) {
                filteredFields[1] = field;
            }
        }

        for (Field field : filteredFields) {
            field.setAccessible(true);
        }

        Field concept = filteredFields[0];
        Field description = filteredFields[1];

        String conceptValue = (String) concept.get(obj);
        String descriptionValue = (String) description.get(obj);

        int randomIndex = random.nextInt(filteredFields.length);

        if (randomIndex == 0) {
            map.put(conceptValue, descriptionValue);
        } else if (randomIndex == 1) {
            map.put(descriptionValue, conceptValue);
        }

        return map;
    }

    private Question getRandomElement(List<Question> list) {
        int randomElement = random.nextInt(list.size());
        Question element = list.get(randomElement);

        return element;
    }

    public void playRandomQuiz(List<Question> list)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("퀴즈를 시작합니다.");
        System.out.println();
        scanner.nextLine();

        while (!list.isEmpty()) {

            Question question = getRandomElement(list);

            int randomIndex = random.nextInt(2);

            if (randomIndex == 0) {
                System.out.println("질문 : " + question.getConcept());
                scanner.nextLine();
                System.out.println("정답 : " + question.getDescription());
                scanner.nextLine();
            } else {
                System.out.println("질문 : " + question.getDescription());
                scanner.nextLine();
                System.out.println("정답 : " + question.getConcept());
                scanner.nextLine();
            }

            // map = getRandomField(question);

            // for (Map.Entry<String, String> entry : map.entrySet()) {
            //     System.out.println("질문 : " + entry.getKey());
            //     scanner.nextLine();
            //     System.out.println("정답 : " + entry.getValue());
            //     scanner.nextLine();

            // }

            list.remove(question);

        }

        System.out.println("퀴즈를 종료합니다.");
        scanner.close();
    }

}
