package org.snoflo.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

public interface TableRepository {

    public List<String> findAllTable();
    
    public void createTable(String fileName) throws SQLException;

    public void truncateTable(String fileName);
} 
