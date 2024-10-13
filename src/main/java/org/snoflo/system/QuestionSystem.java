package org.snoflo.system;

import org.snoflo.builder.AppSystemBuilder;
import org.snoflo.builder.FinderSystemBuilder;
import org.snoflo.builder.QuestionSystemBuilder;
import org.snoflo.controller.AppController;
import org.snoflo.controller.FinderController;
import org.snoflo.controller.QuestionController;

public class QuestionSystem implements AppSystem {

    private QuestionController questionController;

    public QuestionSystem(QuestionSystemBuilder builder) {
        this.questionController = builder.getController();
    }

    @Override
    public void startSystem() {
        questionController.start();
    }
}
