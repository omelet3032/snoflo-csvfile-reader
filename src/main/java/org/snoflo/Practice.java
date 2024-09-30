package org.snoflo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Practice {

    private static List<String> beforeList = new ArrayList<>();
    private static List<String> setList(List<String> list) {
        list.add("유진우");
        list.add("박설화");
        list.add("대장군");
        list.add("진장군");
        list.add("진장군1");
        list.add("진장군2");
        list.add("진장군3");
        return list;
    }

    public static void main(String[] args) {

        List<String> list = setList(beforeList);

        String result = getRandomElement(list);

        System.out.println("list : " + list);
        System.out.println("result : " + result);

        list.remove(result);

        System.out.println("remove list : " + list);

    }

    public static <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        int randomElement = random.nextInt(list.size());
        T element = list.get(randomElement);
        list.remove(randomElement);

        return element;
    }
}
