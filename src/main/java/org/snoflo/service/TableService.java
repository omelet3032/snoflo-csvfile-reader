package org.snoflo.service;

import java.sql.SQLException;
import java.util.List;

import org.snoflo.repository.TableRepository;

public class TableService {

    private TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<String> findRegisterdTable() {

        return this.tableRepository.findAllTable();

    }
}
