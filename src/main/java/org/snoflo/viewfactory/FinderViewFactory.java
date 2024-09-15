package org.snoflo.viewfactory;

import org.snoflo.view.AppView;
import org.snoflo.view.FinderView;

public class FinderViewFactory implements AppViewFactory {

    @Override
    public AppView createAppView() {
        return new FinderView();
    }

    
}
