package org.snoflo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileReader2 {

    public static List<String> getCsvFileNames(String directory) throws IOException {
        // 특정 디렉토리 경로 지정
        Path dirPath = Paths.get(directory);

        // .csv 파일 필터링 및 이름 추출
        return Files.list(dirPath)
                .filter(Files::isRegularFile)  // 파일만 필터링
                .filter(path -> path.toString().endsWith(".csv"))  // .csv 확장자 필터링
                .map(path -> path.getFileName().toString())  // 파일 이름 추출
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        try {
            List<String> csvFiles = getCsvFileNames("src/main/resources/csv");

            // 결과 출력
            csvFiles.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
