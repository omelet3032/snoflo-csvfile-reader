package org.snoflo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Practice {
    
    public List<String> getCsvFileNames(String directory) throws IOException {

        Path dirPath = Paths.get(directory);
     
        return Files.list(dirPath)
        .filter(file -> Files.isRegularFile(file))
        .filter(path -> path.toString().endsWith(".csv"))
        .map(path -> path.getFileName().toString())
        .collect(Collectors.toList());
    }

    public static void main(String[] args) {
       
        File directory = new File("src/main/resources/csv/");

        List<String> fileNames = new ArrayList<>();
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    }
                }
            }
        }

        System.out.println(fileNames.get(0));
    }
}
