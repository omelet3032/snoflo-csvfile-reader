package org.snoflo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.snoflo.service.FinderService;
import org.snoflo.view.FinderView;

// csvFile을 세팅하는 도메인 컨트롤러
public class FinderController extends AppController {

    private FileFinder fileFinder;
    private FinderService finderService;
    private FinderView finderView;

    public FinderController(FileFinder fileFinder, FinderService finderService, FinderView finderView) {
        this.fileFinder = fileFinder;
        this.finderService = finderService;
        this.finderView = finderView;
    }
    
    public FinderController(FinderService finderService, FinderView finderView) {
        this.fileFinder = new FileFinder();
        this.finderService = finderService;
        this.finderView = finderView;
    }

    public void sendSelectedFileToService() {
        Path selectedFile = selectFile();
        finderService.sendSelectedFile(selectedFile);
    }

    private Path selectFile() {
        Path selectedFolder = executeFindFolder();
        Path selectedFile = executeFindFile(selectedFolder);

        // FileDto fileDto = new FileDto(selectedFile);

        return selectedFile;
    }

    private Path executeFindFolder() {
        finderView.showPromptFolder();

        List<Path> folderList = fileFinder.getFolderList();

        finderView.showSelectFolder(folderList);
        int number = scanner.nextInt();
        scanner.nextLine();

        Path selectedFolder = folderList.get(number);
        return selectedFolder;
    }

    private Path executeFindFile(Path selectedFolder) {
        finderView.showPromptCsvFile();

        List<Path> fileList = fileFinder.getFileList(selectedFolder);

        finderView.showSelectCsvFile(fileList);
        int number = scanner.nextInt();
        scanner.nextLine();

        Path selectedFile = fileList.get(number);
        return selectedFile;
    }

    static class FileFinder {

        public List<Path> getFolderList() {
            Path dirPath = Paths.get(System.getProperty("user.dir"));
            int maxDepth = 2;

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

}
