package org.snoflo.function;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.snoflo.domain.Question;
import org.snoflo.view.RandomQuizView;

public class RandomQuiz {

    public void playRandomQuiz(List<Question> questionList, RandomQuizView questionView,
            Scanner scanner) {

        Iterator<Question> iterator = questionList.iterator();
        
        while (iterator.hasNext()) {
            
            Question question = iterator.next();
            questionView.showResultQuestionField(question.getQuestion());
            scanner.nextLine();
            questionView.showResultAnswerField(question.getAnswer());
            scanner.nextLine();

            iterator.remove();
        }

    }

}
