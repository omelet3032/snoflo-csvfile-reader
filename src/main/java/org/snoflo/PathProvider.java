package org.snoflo;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathProvider {

    // 파일 제공 -> dev2/csv/library.csv
    public Path getCsvFile(String selectFolder, String csvFile) {
        return getDirectoryPath(selectFolder).resolve(csvFile);
    }

    // 경로 제공 -> dev2/csv
    public Path getDirectoryPath(String selectFolder) {
        return Paths.get(System.getProperty("user.dir"), selectFolder);
    }

}
