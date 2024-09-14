package org.snoflo.service;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.model.Question;

public interface QuestionService {

    Question findConceptById(int id);

    List<String> findCsvFiles();

    List<Path> findFolder();

    void setCsvFile(Path folder, String csvFile);

}
