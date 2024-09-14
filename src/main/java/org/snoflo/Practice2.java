package org.snoflo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Practice2 {
    
    public static List<String> getFolderNames() {
        
        Path dirPath = Paths.get(System.getProperty("user.dir"));
        int maxDepth = 1;
        List<String> folderList;

        try {
            Files.walk(dirPath, maxDepth).filter(Files::isDirectory).forEach(System.out::println);

        } catch(IOException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static void main(String[] args) {
        getFolderNames();
    }
}
