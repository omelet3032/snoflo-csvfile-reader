package org.snoflo.commander;

import org.snoflo.controller.RandomQuizController;

public class RandomQuizCommander implements AppCommander{
    
    private RandomQuizController randomQuizController;

    public RandomQuizCommander(RandomQuizController randomQuizController) {
        this.randomQuizController = randomQuizController;
    }

    @Override
    public void executeCommander() {
        this.randomQuizController.start();
        return;
    }
}
