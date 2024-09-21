package org.snoflo.dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariConnectionPool {

    private static HikariDataSource dataSource;

    private static MySqlConfig mysqlConfig;

    static {
        mysqlConfig = new MySqlConfig();

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(mysqlConfig.getJdbcUrl()); // JDBC URL
        config.setUsername(mysqlConfig.getUser()); // DB 사용자
        config.setPassword(mysqlConfig.getPassword()); // DB 비밀번호
        config.setMaximumPoolSize(10); // 최대 커넥션 수
        config.setMinimumIdle(5); // 최소 유휴 커넥션 수
        config.setIdleTimeout(60000); // 유휴 상태에서의 타임아웃 (밀리초)
        config.setMaxLifetime(1800000); // 커넥션 최대 수명 (밀리초)
        config.setConnectionTimeout(30000); // 커넥션을 가져오는데 허용되는 최대 대기 시간 (밀리초)

        // DataSource 초기화
        dataSource = new HikariDataSource(config);
    }

    // 커넥션 풀에서 커넥션을 가져오는 메서드
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // 커넥션 풀을 종료하는 메서드
    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
