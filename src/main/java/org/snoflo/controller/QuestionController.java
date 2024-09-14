package org.snoflo.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import org.snoflo.dto.CsvFileDto;
import org.snoflo.repository.CsvFilesFinder;
import org.snoflo.model.Question;
import org.snoflo.repository.QuestionDataConverter;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.AppView;

public class QuestionController extends AppController {

    private QuestionService appService;

    public QuestionController() {
        this.view = new AppView();
        this.scanner = new Scanner(System.in);

        this.appService = new QuestionServiceImpl();
        executeMainMenu();
    }

    private void executeMainMenu() {
        view.showPromptMainMenu();
        view.showSelectMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1 -> executeFindFolders();
            case 2 -> executeFindById();
            default -> executeMainMenu();
        }
    }

    private void executeFindFolders() {
        view.showPromptFolder();
        List<Path> folderList = appService.findFolder();
        view.showSelectFolder(folderList);
        int number = scanner.nextInt();
        scanner.nextLine();
        Path selectedFolder = folderList.get(number);
        appService.setCsvFile(selectedFolder,null);
        executeFindCsvFiles();
    }

    private void executeFindCsvFiles() {
        view.showPromptCsvFile();
        List<String> csvFiles = appService.findCsvFiles();
        view.showSelectCsvFile(csvFiles);
        int number = scanner.nextInt();
        scanner.nextLine();
        String selectedFile = csvFiles.get(number);
        System.out.println("선택한 파일 : " + selectedFile.toString());

        appService.setCsvFile(null,selectedFile);
    }





    private void executeFindById() {
        view.showPromptFindById();
        int id = scanner.nextInt();
        scanner.nextLine();
        Question conceptById = appService.findConceptById(id);
        view.showResultFindById(conceptById);
    }

}
