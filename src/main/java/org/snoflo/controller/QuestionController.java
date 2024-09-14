package org.snoflo.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.snoflo.dto.CsvFileDto;
import org.snoflo.repository.CsvFilesFinder;
import org.snoflo.model.Question;
import org.snoflo.repository.QuestionDataConverter;
import org.snoflo.service.CsvFilesFinderService;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.AppView;

public class QuestionController extends AppController {

    private QuestionService appService;
    private CsvFilesFinderService finderService;

    public QuestionController() {
        this.view = new AppView();
        this.scanner = new Scanner(System.in);

        // this.appService = new QuestionServiceImpl();
        this.finderService = new CsvFilesFinderService();

        CsvFileDto csvFileDto = setFolderAndFile();
        this.appService = new QuestionServiceImpl(csvFileDto);
        executeMainMenu();
    }

    private void executeMainMenu() {
        view.showPromptMainMenu();
        view.showSelectMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1 -> executeFindById();
            default -> executeMainMenu();
        }
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

    public void executeFindById() {
        view.showPromptFindById();
        int id = scanner.nextInt();
        scanner.nextLine();
        Question conceptById = appService.findConceptById(id);
        view.showResultFindById(conceptById);
    }

}
