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

        Path selectedFolder = executeFindFolder();

        Path selectedFile = executeFindFile(selectedFolder);

        String fileName = selectedFile.getFileName().toString();
        fileName = fileName.replace(".csv", "").toLowerCase();


        String fileName2 = selectedFile.getFileName().toString();

        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir);
        String csvFileName = currentDir + "/" + "csv" + "/" + fileName2;
        System.out.println("csvFileName : " + csvFileName);
        // List<Question> csvRowList = csvFileReader.readCsvFile(selectedFile);
        List<Question> csvRowList = csvFileReader.readCsvFile(csvFileName);

        String tableName = finderService.findRegisteredTable(fileName);

        if (tableName.isBlank()) {
            finderService.createQuestionTable(fileName);
        } else {
            finderService.truncateQuestionTable(fileName);
        }

        finderService.saveQuestionList(csvRowList, fileName);

    }

    private Path executeFindFolder() {
        finderView.showPromptFolder();

        List<Path> folderList = csvFileFinder.getFolderList();

        finderView.showSelectFolder(folderList);
        int number = scanner.nextInt();
        scanner.nextLine();

        Path selectedFolder = folderList.get(number);
        return selectedFolder;
    }

    private Path executeFindFile(Path selectedFolder) {
        finderView.showPromptCsvFile();

        List<Path> fileList = csvFileFinder.getFileList(selectedFolder);

        finderView.showSelectCsvFile(fileList);
        int number = scanner.nextInt();
        scanner.nextLine();

        Path selectedFile = fileList.get(number);
        return selectedFile;
    }

}
