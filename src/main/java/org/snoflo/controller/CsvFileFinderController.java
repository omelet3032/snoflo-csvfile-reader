package org.snoflo.controller;

import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.snoflo.function.CsvFileFinder;
import org.snoflo.view.CsvFileFinderView;

public class CsvFileFinderController {

    private CsvFileFinderView registerView;

    private CsvFileFinder csvFileFinder;

    private Scanner scanner;

    public CsvFileFinderController(CsvFileFinderView registerView, CsvFileFinder csvFileFinder, Scanner scanner) {
        this.registerView = registerView;
        this.csvFileFinder = csvFileFinder;
        this.scanner = scanner;
    }

    public Path executeFinderController() {
        Path selectedFolder = searchFolder();
        Path selectedCsvFile = searchCsvFile(selectedFolder);
        return selectedCsvFile;
    }

    private Path searchFolder() {
        registerView.showPromptFolder();

        List<Path> folderList = csvFileFinder.getFolderList();

        registerView.showSelectFolder(folderList);
        // int number = scanner.nextInt();

        int number = -1;
        while (true) {
            try {
                number = scanner.nextInt();
                if (number >= 0 && number < folderList.size()) {
                    break; // 유효한 입력일 경우 반복 종료
                } else {
                    System.out.println("유효한 숫자를 입력하세요."); // 범위 체크
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력하세요.");
                scanner.next(); // 잘못된 입력을 소비하여 무한 루프 방지
            }
        }

        scanner.nextLine();

        Path selectedFolder = folderList.get(number);

        return selectedFolder;
    }

    private Path searchCsvFile(Path selectedFolder) {
        registerView.showPromptCsvFile();

        List<Path> fileList = csvFileFinder.getFileList(selectedFolder);

        registerView.showSelectCsvFile(fileList);
        // int number = scanner.nextInt();

        int number = -1;
        while (true) {
            try {
                number = scanner.nextInt();
                if (number >= 0 && number < fileList.size()) {
                    break; // 유효한 입력일 경우 반복 종료
                } else {
                    System.out.println("유효한 숫자를 입력하세요."); // 범위 체크
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력하세요.");
                scanner.next(); // 잘못된 입력을 소비하여 무한 루프 방지
            }
        }

        scanner.nextLine();

        Path selectedFile = fileList.get(number);

        return selectedFile;

    }
}
