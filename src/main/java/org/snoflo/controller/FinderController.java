package org.snoflo.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import org.snoflo.function.CsvFileFinder;
import org.snoflo.service.FinderService;
import org.snoflo.view.FinderView;

public class FinderController {

    private CsvFileFinder csvFileFinder;

    private FinderView finderView;

    private FinderService finderService;

    private Scanner scanner;

    public FinderController(Scanner scanner, CsvFileFinder csvFileFinder, FinderService finderService,
            FinderView finderView) {
        this.csvFileFinder = csvFileFinder;
        this.finderService = finderService;
        this.finderView = finderView;
        this.scanner = scanner;
    }

    public void start() {
        Path selectedFolder = searchFolder();
        Path selectedCsvFile = searchCsvFile(selectedFolder);

        toDatabase(selectedCsvFile);
        return;
    }

    private void toDatabase(Path selectedFile) {

        String csvFileName = convertPathToString(selectedFile);

        String tableName = finderService.findRegisteredTable(csvFileName);

        if (tableName.isBlank()) {
            finderView.showPromptRegisterFile(csvFileName);
            finderService.createQuestionTable(csvFileName);

        } else {

            finderView.showSelectOverwriteFile(csvFileName);

            String answer;

            while (true) {

                answer = scanner.nextLine();

                if (answer.equals("Y")) {
                    finderService.truncateQuestionTable(csvFileName);
                    break;
                } else if (answer.equals("n")) {
                    finderView.showPromptReturnMainMenu();
                    return;
                } else {
                    finderView.showPromptYorN();
                }
            }   

        }

        finderView.showPromptSaveCsvFileToDatabase(csvFileName);
        finderService.saveQuestionList(selectedFile, csvFileName);

    }

    private String convertPathToString(Path selectedFile) {
        String csvFileName = selectedFile.getFileName().toString();
        csvFileName = csvFileName
                .replace(".csv", "")
                .toLowerCase();

        return csvFileName;

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
