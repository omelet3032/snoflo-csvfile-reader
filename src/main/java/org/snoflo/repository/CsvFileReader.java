package org.snoflo.repository;

import java.util.List;

import org.snoflo.dto.FileDto;

public interface CsvFileReader {

    List<String[]> readCsvFile(FileDto csvFileDto);
}
