package org.snoflo.strategy;

import java.sql.SQLException;

public class AppContext {
    
    public void runContext(AppStrategy appStrategy) {
        appStrategy.runStrategy();
    };
}
