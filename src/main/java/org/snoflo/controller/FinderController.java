package org.snoflo.controller;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.domain.Question;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.service.FinderServiceImpl;
import org.snoflo.view.FinderView;

// csvFile을 세팅하는 도메인 컨트롤러
// file 선택후 save 메서드로 db에 csvfile을 저장하는 책임

public class FinderController extends AppController implements CommonControllerInterface {

    private CsvFileParser csvFileReader;
    private CsvFileFinder csvFileFinder;

    private FinderView finderView;

    private FinderServiceImpl finderService;

    public FinderController(CsvFileParser csvFileReader, CsvFileFinder csvFileFinder, FinderServiceImpl finderService,
            FinderView finderView) {
        this.csvFileReader = csvFileReader;
        this.csvFileFinder = csvFileFinder;
        this.finderService = finderService;
        this.finderView = finderView;
    }

    public void start() {
        Path registerdCsvFile = registerCsvFile();
        toDatabase(registerdCsvFile);
    }

    private Path registerCsvFile() {

        Path selectedFile = searchCsvFile(searchFolder());

        return selectedFile;
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
