package org.snoflo.function;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileFinder {

    public List<Path> getFolderList() {
        Path dirPath = Paths.get(System.getProperty("user.dir"));
        int maxDepth = 1;

        try {
            return Files.walk(dirPath, maxDepth).filter(Files::isDirectory).collect(Collectors.toList());
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
