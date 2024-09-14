package org.snoflo.repository;

import java.util.List;

import org.snoflo.dto.CsvFileDto;

public interface CsvFileReader {

    List<String[]> readCsvFile(CsvFileDto csvFileDto);
}
