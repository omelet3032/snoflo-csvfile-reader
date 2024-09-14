package org.snoflo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/* 
 * 이 클래스가 관계를 맺어야 하는 클래스는 CsvFileDto와 AppView
 * 그리고 Service클래스 Controller 클래스
 */
public class CsvFilesFinderService {

    public List<Path> getFolderNames() {
        Path dirPath = Paths.get(System.getProperty("user.dir"));
        int maxDepth = 2;

        try {
            return Files.walk(dirPath, maxDepth).filter(Files::isDirectory).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public List<Path> getFileNames(Path selectedFolder) {

        try {
            return Files.list(selectedFolder)
                    .filter(file -> Files.isRegularFile(file))
                    .filter(path -> path.toString().endsWith(".csv"))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

}
