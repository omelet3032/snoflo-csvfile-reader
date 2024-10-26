package org.snoflo.function;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

public class TableInformation {

    private HikariDataSource dataSource;

    public TableInformation(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> findAllTable() {
        List<String> list = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, "PUBLIC", "%", new String[] { "TABLE"
            });

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                list.add(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
