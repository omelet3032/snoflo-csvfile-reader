package org.snoflo.function;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.snoflo.domain.CsvFileRow;
import org.snoflo.dto.RandomQuestionDto;
import org.snoflo.view.RandomQuizView;

public class RandomQuiz {

    private RandomQuestion randomQuestion;
    
    public RandomQuiz() {
        this.randomQuestion = new RandomQuestion();
    }

    public List<CsvFileRow> playRandomQuiz(List<CsvFileRow> list, RandomQuizView questionView, Scanner scanner) {

        while (!list.isEmpty()) {
            Map<CsvFileRow, RandomQuestionDto> map = randomQuestion.getRandomQuestion(list);

            Set<CsvFileRow> key = map.keySet();
            CsvFileRow question = key.iterator().next();

            for (RandomQuestionDto dto : map.values()) {
                questionView.showResultQuestionField(dto.question());
                scanner.nextLine();
                questionView.showResultAnswerField(dto.answer());
                scanner.nextLine();
            }

            list.remove(question);

        }

        return list;
    }
}
