package org.snoflo.view;

import java.util.List;

import org.snoflo.domain.Question;

public class QuestionView {

    public void showResultFindAll(List<Question> list) {
        System.out.println("--------------------------------");
        for (Question question : list) {
            System.out.println(question.toString());
        }
    }

    public void showPromptFindById() {
        System.out.println("-----------------------------");
        System.out.println("----- id로 검색하기 -----");
        System.out.println("-----------------------------");
    }

    public void showResultFindById(Question concept) {
        System.out.println();
        System.out.println("conceptById : " + concept);
    }
}
