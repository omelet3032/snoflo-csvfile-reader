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
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.AppView;

public class QuestionController extends AppController {

    private QuestionService appService;

    public QuestionController() {
        this.view = new AppView();
        this.scanner = new Scanner(System.in);
        this.appService = new QuestionServiceImpl();
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
        List<Path> folderList = new ArrayList<>();
        List<String> fileList = new ArrayList<>();

        Path selectedFolder = executeFindFolders(folderList);
        String selectedFile = executeFindCsvFiles(fileList, selectedFolder);

        CsvFileDto csvFileDto = new CsvFileDto(selectedFolder, selectedFile);
        return csvFileDto;
    }

    private Path executeFindFolders(List<Path> folderList) {
        view.showPromptFolder();
        folderList = appService.findFolder();
        view.showSelectFolder(folderList);
        int number = scanner.nextInt();
        scanner.nextLine();
        Path selectedFolder = folderList.get(number);
        // 이 부분에서 csv폴더명만이 아닌 전체 경로를 반환하기 때문에 이후 모든 클래스에 절대 경로를 전송한다,
        // 그래서 csvFile을 찾을 수가 없다. 
        // java.nio.file.NoSuchFileException: /home/omelet1/dev2/snoflo-csvfile-reader/home/omelet1/dev2/snoflo-csvfile-reader/csv
        return selectedFolder;
    }

    private String executeFindCsvFiles(List<String> fileList, Path selectedFolder) {
        view.showPromptCsvFile();
        fileList = appService.findCsvFiles(selectedFolder);
        System.out.println("controller path : " + fileList.toString());

        view.showSelectCsvFile(fileList);
        int number = scanner.nextInt();
        scanner.nextLine();
        String selectedFile = fileList.get(number);
        return selectedFile;
    }

    private void executeFindById() {
        view.showPromptFindById();
        int id = scanner.nextInt();
        scanner.nextLine();
        Question conceptById = appService.findConceptById(id);
        view.showResultFindById(conceptById);
    }

}
