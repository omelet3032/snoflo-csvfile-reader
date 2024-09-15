package org.snoflo.viewfactory;

import org.snoflo.view.AppView;

public interface AppViewFactory {
   
    // default AppView renderAppView() {
    //     AppView view = createAppView();
    //     System.out.println("팩토리를 통해 생성된 view : " + view.toString());
    //     return view;
    // }

    public AppView createAppView();

}
