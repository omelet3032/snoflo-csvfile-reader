package org.snoflo.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import org.snoflo.application.ApplicationStrategy;
import org.snoflo.domain.Question;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.service.FinderService;
import org.snoflo.view.FinderView;

// csvFile을 세팅하는 도메인 컨트롤러
// file 선택후 save 메서드로 db에 csvfile을 저장하는 책임

public class FinderController implements ApplicationStrategy { 

    private CsvFileParser csvFileReader;
    private CsvFileFinder csvFileFinder;

    private FinderView finderView;

    private FinderService finderService;

    private Scanner scanner;

    public FinderController(Scanner scanner, CsvFileParser csvFileReader, CsvFileFinder csvFileFinder, FinderService finderService,
            FinderView finderView) {
        this.csvFileReader = csvFileReader;
        this.csvFileFinder = csvFileFinder;
        this.finderService = finderService;
        this.finderView = finderView;
        this.scanner = scanner;
    }

    @Override
    public void start() {
        Path selectedFolder = searchFolder();
        Path selectedCsvFile = searchCsvFile(selectedFolder);
        
        toDatabase(selectedCsvFile);
    }

    private void toDatabase(Path selectedFile) {

        String csvFileName = selectedFile.getFileName().toString();
        csvFileName = csvFileName
                .replace(".csv", "")
                .toLowerCase();

        String tableName = finderService.findRegisteredTable(csvFileName);

        if (tableName.isBlank()) {
            finderView.showPromptRegisterFile(csvFileName);
            finderService.createQuestionTable(csvFileName);

        } else {
            finderView.showSelectOverwriteFile(csvFileName);
            String answer = scanner.nextLine();

            if (answer.equals("Y")) {
                finderService.truncateQuestionTable(csvFileName);

            } else if (answer.equals("n")) {
                System.out.println("메인 메뉴로 돌아갑니다.");
                return;
            }
        }

        List<Question> csvRowList = csvFileReader.readCsvFile(selectedFile);
        finderService.saveQuestionList(csvRowList, csvFileName);

    }

    private Path searchFolder() {
        finderView.showPromptFolder();

        List<Path> folderList = csvFileFinder.getFolderList();

        finderView.showSelectFolder(folderList);
        int number = scanner.nextInt();
        scanner.nextLine();

        Path selectedFolder = folderList.get(number);

        return selectedFolder;
    }

    private Path searchCsvFile(Path selectedFolder) {
        finderView.showPromptCsvFile();

        List<Path> fileList = csvFileFinder.getFileList(selectedFolder);

        finderView.showSelectCsvFile(fileList);
        int number = scanner.nextInt();
        scanner.nextLine();

        Path selectedFile = fileList.get(number);

        return selectedFile;

    }

}
