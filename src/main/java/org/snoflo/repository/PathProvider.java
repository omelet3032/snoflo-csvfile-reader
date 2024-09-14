package org.snoflo.repository;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathProvider {

    public Path getCsvFile(String selectFolder, String csvFile) {
        return getDirectoryPath(selectFolder).resolve(csvFile);
    }

    public Path getDirectoryPath(String selectFolder) {
        return Paths.get(System.getProperty("user.dir"), selectFolder);
    }

}
