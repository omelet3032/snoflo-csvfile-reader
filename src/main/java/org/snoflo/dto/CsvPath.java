package org.snoflo.dto;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvPath {

    String folderName = "csv";
    Path dirPath = Paths.get(System.getProperty("user.dir"), folderName);
    String name = dirPath.toString();
}
