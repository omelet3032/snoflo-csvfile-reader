package org.snoflo.viewfactory;

import org.snoflo.view.AppView;
import org.snoflo.view.MainView;

public class MainViewFactory implements AppViewFactory {

    @Override
    public AppView createAppView() {
        return new MainView();
    }

}
