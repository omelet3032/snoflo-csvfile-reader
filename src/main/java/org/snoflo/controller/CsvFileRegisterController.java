package org.snoflo.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import org.snoflo.service.FileRegisterService;
import org.snoflo.view.CsvFileRegisterView;

public class CsvFileRegisterController {

    private CsvFileRegisterView registerView;

    private FileRegisterService finderService;

    private Scanner scanner;

    public CsvFileRegisterController(Scanner scanner, FileRegisterService finderService, CsvFileRegisterView registerView) {
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
