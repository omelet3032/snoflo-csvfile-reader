package org.snoflo.repository;

import java.nio.file.Path;

import org.snoflo.db.CsvFileManager;

public class FinderRepository {

    private CsvFileManager csvFileManager;

    public FinderRepository (CsvFileManager csvFileManager) {
        this.csvFileManager = csvFileManager;
    }
    
    public void save(Path selectedFile) {
        this.csvFileManager.generateData(selectedFile);
    }
}
