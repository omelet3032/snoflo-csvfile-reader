package org.snoflo.controller;

import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.snoflo.function.FileFinder;
import org.snoflo.view.FileFinderView;

public class FileFinderController {

    private FileFinderView finderView;

    private FileFinder fileFinder;

    private Scanner scanner;

    public FileFinderController(FileFinderView finderView, FileFinder fileFinder, Scanner scanner) {
        this.finderView = finderView;
        this.fileFinder = fileFinder;
        this.scanner = scanner;
    }

    public Path executeFinderController() {
        Path selectedFolder = searchFolder();
        Path selectedCsvFile = searchCsvFile(selectedFolder);
        return selectedCsvFile;
    }

    private Path searchFolder() {
        finderView.showPromptFolder();

        List<Path> folderList = this.fileFinder.getFolderList();

        finderView.showSelectFolder(folderList);
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
        finderView.showPromptCsvFile();

        List<Path> fileList = this.fileFinder.getFileList(selectedFolder);

        finderView.showSelectCsvFile(fileList);
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
