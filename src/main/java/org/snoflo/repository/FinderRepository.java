package org.snoflo.repository;

import java.nio.file.Path;

import org.snoflo.db.CsvFileManager;

public class FinderRepository {
    
    private CsvFileManager csvFileManager;

    public FinderRepository() {
        this.csvFileManager = new CsvFileManager();
    }

    public void save(Path selectedFile) {
        csvFileManager.generateData(selectedFile);
    }
}
