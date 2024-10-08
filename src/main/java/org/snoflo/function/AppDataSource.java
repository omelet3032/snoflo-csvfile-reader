package org.snoflo.function;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class AppDataSource {

    private AppDataSource() {
    }

    private static class HikariDataSourceHolder {
        private static final HikariConfig config = new HikariConfig("/application-h2.properties");
        private static final HikariDataSource INSTANCE = new HikariDataSource(config);
    }

    public static HikariDataSource getInstance() {
        return HikariDataSourceHolder.INSTANCE;
    }

}
