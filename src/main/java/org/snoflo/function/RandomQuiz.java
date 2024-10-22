package org.snoflo.function;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.snoflo.domain.Row;
import org.snoflo.dto.QuestionDto;
import org.snoflo.view.RandomQuizView;

public class RandomQuiz {

    private RandomQuestionGenerator randomQuestionGenerator;

    public RandomQuiz() {
        this.randomQuestionGenerator = new RandomQuestionGenerator();
    }

    public List<QuestionDto> playRandomQuiz2(List<QuestionDto> questionList, RandomQuizView questionView,
            Scanner scanner) {

        Iterator<QuestionDto> iterator = questionList.iterator();
        
        while (iterator.hasNext()) {
            
            QuestionDto dto = iterator.next();
            questionView.showResultQuestionField(dto.questionValue());
            scanner.nextLine();
            questionView.showResultAnswerField(dto.answerValue());
            scanner.nextLine();

            iterator.remove();
        }
        // while (!questionList.isEmpty()) {
        // // randomQuestionGenerator.getRandomQuestion(list);
        // // List<QuestionDto> questionList =
        // randomQuestionGenerator.getRandomQuestion2(list);

        // for (QuestionDto dto : questionList) {
        // questionView.showResultQuestionField(dto.questionValue());
        // scanner.nextLine();
        // questionView.showResultAnswerField(dto.answerValue());
        // scanner.nextLine();
        // }

        // // list.remove(question);

        // }

        return questionList;
    }

    public List<Row> playRandomQuiz(List<Row> list, RandomQuizView questionView,
            Scanner scanner) {

        while (!list.isEmpty()) {
            Map<Row, QuestionDto> map = randomQuestionGenerator.getRandomQuestion(list);

            Set<Row> key = map.keySet();
            Row question = key.iterator().next();

            for (QuestionDto dto : map.values()) {
                questionView.showResultQuestionField(dto.questionValue());
                scanner.nextLine();
                questionView.showResultAnswerField(dto.answerValue());
                scanner.nextLine();
            }

            list.remove(question);

        }

        return list;
    }
}
