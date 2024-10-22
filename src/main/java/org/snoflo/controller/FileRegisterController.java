package org.snoflo.controller;

import java.nio.file.Path;
import java.util.Scanner;

import org.snoflo.service.FileRegisterService;
import org.snoflo.view.FileRegisterView;

public class FileRegisterController {

    private FileRegisterView registerView;

    private FileRegisterService finderService;

    private Scanner scanner;

    public FileRegisterController(Scanner scanner, FileRegisterService finderService, FileRegisterView registerView) {
        this.finderService = finderService;
        this.registerView = registerView;
        this.scanner = scanner;
    }

    public void executeRegisterController(Path selectedCsvFile) {
        String csvFileName = convertPathToString(selectedCsvFile);
        toDatabase(selectedCsvFile, csvFileName);
    }

    private void toDatabase(Path selectedCsvFile, String csvFileName) {

        String tableName = finderService.findRegisteredTable(csvFileName);

        if (tableName.isBlank()) {
            registerView.showPromptRegisterFile(csvFileName);
            finderService.createQuestionTable(csvFileName);
        }

        registerView.showSelectOverwriteFile(csvFileName);

        String answer;

        while (true) {

            answer = scanner.nextLine();

            if (answer.equals("Y")) {
                finderService.truncateQuestionTable(csvFileName);
                break;
            } else if (answer.equals("n")) {
                registerView.showPromptReturnMainMenu();
                return;
            } else {
                registerView.showSelectYorN();
            }
        }

        registerView.showPromptSaveCsvFileToDatabase(csvFileName);
        finderService.saveQuestionList(selectedCsvFile, csvFileName);

    }

    private String convertPathToString(Path selectedFile) {
        String csvFileName = selectedFile.getFileName().toString();
        csvFileName = csvFileName
                .replace(".csv", "")
                .toLowerCase();

        return csvFileName;

    }

}
