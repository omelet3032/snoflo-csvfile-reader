package org.snoflo.repository;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.dto.CsvFileDto;

public interface CsvFileReader {

    // List<String[]> readCsvFile(Path folderName, String fileName);
    List<String[]> readCsvFile(CsvFileDto csvFileDto);
}
