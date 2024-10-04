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

    public RandomQuestion() {
        this.random = new Random();
    }

    private Question getRandomElement(List<Question> list) {
        int randomElement = random.nextInt(list.size());
        Question element = list.get(randomElement);
        return element;
    }

    public void playRandomQuiz(List<Question> list) throws IllegalArgumentException, IllegalAccessException {

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("퀴즈를 시작합니다.");
        System.out.println();
        scanner.nextLine();
    
        
        while (!list.isEmpty()) {

    
            Question question = getRandomElement(list);
            Field[] fields = Question.class.getDeclaredFields();
            int randomIndex = random.nextInt(fields.length);
    
            fields[randomIndex].setAccessible(true);
            Object questionField = fields[randomIndex].get(question);
    
            int otherIndex = (randomIndex + 1) % fields.length;
            fields[otherIndex].setAccessible(true);
            Object otherField = fields[otherIndex].get(question);
    
            System.out.println("질문 : " + questionField);
            scanner.nextLine();
            System.out.println("정답 : " + otherField);
            scanner.nextLine();



            // int randomIndex = random.nextInt(2);

            // if (randomIndex == 0) {
            //     System.out.println("질문 : " + question.getConcept());
            //     scanner.nextLine();
            //     System.out.println("정답 : " + question.getDescription());
            //     scanner.nextLine();
            // } else {
            //     System.out.println("질문 : " + question.getDescription());
            //     scanner.nextLine();
            //     System.out.println("정답 : " + question.getConcept());
            //     scanner.nextLine();
            // }

            list.remove(question);

        }

        System.out.println("퀴즈를 종료합니다.");
        scanner.close();
    }

}
