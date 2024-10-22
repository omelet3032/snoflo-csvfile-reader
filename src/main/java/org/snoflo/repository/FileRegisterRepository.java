package org.snoflo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.snoflo.domain.Row;

public interface FileRegisterRepository {

    public void save(List<Row> list, String fileName);

}
