package org.snoflo.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import org.snoflo.dto.CsvFileDto;
import org.snoflo.service.CsvFilesFinderService;
import org.snoflo.view.AppView;

// csvFile을 세팅하는 도메인 컨트롤러
public class FinderController extends AppController {

    private CsvFilesFinderService finderService;
    
    public FinderController(CsvFilesFinderService finderService, AppView view) {
        super(view);
        this.finderService = finderService;
    }

    public CsvFileDto setFolderAndFile() {
        Path selectedFolder = executeFindFolder();
        Path selectedFile = executeFindCsvFile(selectedFolder);

        CsvFileDto csvFileDto = new CsvFileDto(selectedFile);
        return csvFileDto;
    }

    private Path executeFindFolder() {
        view.showPromptFolder();

        List<Path> folderList = finderService.getFolderNames();

        view.showSelectFolder(folderList);
        int number = scanner.nextInt();
        scanner.nextLine();

        Path selectedFolder = folderList.get(number);
        return selectedFolder;
    }

    private Path executeFindCsvFile(Path selectedFolder) {
        view.showPromptCsvFile();
        List<Path> fileList = finderService.getFileNames(selectedFolder);

        view.showSelectCsvFile(fileList);
        int number = scanner.nextInt();
        scanner.nextLine();
        Path selectedFile = fileList.get(number);
        return selectedFile;
    }

}