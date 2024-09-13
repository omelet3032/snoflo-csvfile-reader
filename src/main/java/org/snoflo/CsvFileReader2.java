package org.snoflo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileReader2 {

    public static List<String> getCsvFileNames() throws IOException {
        // 특정 디렉토리 경로 지정
        Path dirPath = Paths.get(System.getProperty("user.dir"));

        return Files.walk(dirPath).filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".csv"))
                .map(path -> dirPath.relativize(path).toString())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        try {
            List<String> csvFiles = getCsvFileNames();

            // 결과 출력
            csvFiles.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
