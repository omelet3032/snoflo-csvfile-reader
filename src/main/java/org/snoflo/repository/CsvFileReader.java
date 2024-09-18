package org.snoflo.repository;

import java.nio.file.Path;
import java.util.List;


public interface CsvFileReader {

    List<String[]> readCsvFile(Path selectedFile);
}
