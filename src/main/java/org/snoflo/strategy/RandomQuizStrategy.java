package org.snoflo.strategy;

import org.snoflo.controller.RandomQuizController;

public class RandomQuizStrategy implements AppStrategy{
    
    private RandomQuizController randomQuizController;

    public RandomQuizStrategy(RandomQuizController randomQuizController) {
        this.randomQuizController = randomQuizController;
    }

    @Override
    public void runStrategy() {
        this.randomQuizController.start();
        return;
    }
}
