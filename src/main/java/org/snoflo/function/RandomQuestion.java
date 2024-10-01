package org.snoflo.function;

import java.util.List;
import java.util.Random;

import org.snoflo.domain.Question;

public class RandomQuestion {

     public <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        int randomElement = random.nextInt(list.size());
        T element = list.get(randomElement);
        // list.remove(randomElement);

        return element;
    }
    
    
    public <T> List<T> removeQuestion(List<T> list, T element) {

        list.remove(element);
        return list; 
    }

    public <T> List<T> playRandomQuiz(List<T> list) {

         while(!list.isEmpty()) {

            T question = getRandomElement(list);
            System.out.println("랜덤 출력된 question : " + question.toString());
            System.out.println();
            System.out.println("list의 size : " + list.size());
            System.out.println();
    
            list = removeQuestion(list, question);
            
        }

        return list;
    }
}
