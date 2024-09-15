package org.snoflo.viewfactory;

import org.snoflo.view.AppView;
import org.snoflo.view.QuestionView;

public class QuestionViewFactory implements AppViewFactory {

    @Override
    public AppView createAppView() {
        return new QuestionView();
    }

}
