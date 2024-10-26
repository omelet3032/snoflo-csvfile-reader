package org.snoflo.function;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// 메서드가 짧으니 람다로..? 
public class FileFinder {

    private final Path DIRECTORY_PATH = Paths.get(System.getProperty("user.dir"));
    private final int MAX_DEPTH = 1;

    public List<Path> getFolderList() {

        try {
            return Files.walk(DIRECTORY_PATH, MAX_DEPTH).filter(Files::isDirectory).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public List<Path> getFileList(Path selectedFolder) {

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
