package org.snoflo.dto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/* 
 * 이 클래스가 관계를 맺어야 하는 클래스는 CsvFileDto와 AppView
 * 그리고 Service클래스 Controller 클래스
 */
public class CsvFilesFinder {

    private List<String> fileList;
    private CsvFileDto csvFileDto;

    // 옵셔널 추가
    public List<String> getCsvFileNames() {
        try {
            String folderName = "csv";
            Path dirPath = Paths.get(System.getProperty("user.dir"), folderName);
            
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
