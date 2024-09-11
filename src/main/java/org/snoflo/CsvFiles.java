package org.snoflo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFiles {
    
    public List<String> getCsvFileNames(String directory) throws IOException {

        Path dirPath = Paths.get(directory);
     
        return Files.list(dirPath)
        .filter(file -> Files.isRegularFile(file))
        .filter(path -> path.toString().endsWith(".csv"))
        .map(path -> path.getFileName().toString())
        .collect(Collectors.toList());
    }

}
