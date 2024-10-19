package org.snoflo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.snoflo.domain.CsvFileRow;

public interface FileRegisterRepository {

    public void save(List<CsvFileRow> list, String fileName);

}
