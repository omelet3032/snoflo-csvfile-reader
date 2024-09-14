package org.snoflo.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/* 
 * 이 클래스가 관계를 맺어야 하는 클래스는 CsvFileDto와 AppView
 * 그리고 Service클래스 Controller 클래스
 */
public class CsvFilesFinder {

    private List<String> fileList;
    private List<Path> folderList;

    public List<Path> getFolderNames() {
        List<Path> folderList;
        Path dirPath = Paths.get(System.getProperty("user.dir"));
        int maxDepth = 2;

        try {
            folderList = Files.walk(dirPath, maxDepth)
                    .filter(Files::isDirectory)
                    .collect(Collectors.toList());
            return folderList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    // 옵셔널 추가
    public List<String> getCsvFileNames(String selectFolder) {

        try {
            Path dirPath = Paths.get(System.getProperty("user.dir"), selectFolder);

            fileList = Files.list(dirPath)
                    .filter(file -> Files.isRegularFile(file))
                    .filter(path -> path.toString().endsWith(".csv"))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileList;
    }

    public List<String> getFileNames(Path selectedFolder) {
        try {
            // Path dirPath = Paths.get(System.getProperty("user.dir"), selectedFolder.toString());
            Path dirPath = Paths.get(selectedFolder.toString());
            System.out.println("finder path : " + selectedFolder.toString());

            fileList = Files.list(dirPath)
                    .filter(file -> Files.isRegularFile(file))
                    .filter(path -> path.toString().endsWith(".csv"))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileList;
    }

}
