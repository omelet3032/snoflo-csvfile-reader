package org.snoflo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.snoflo.domain.Question;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileParser;
import org.snoflo.function.DbTableManager;
import org.snoflo.repository.FinderRepository;
import org.snoflo.service.FinderService;
import org.snoflo.view.FinderView;
import org.snoflo.view.MainView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// csvFile을 세팅하는 도메인 컨트롤러
// file 선택후 save 메서드로 db에 csvfile을 저장하는 책임

public class FinderController extends AppController implements CommonControllerInterface {

    private CsvFileParser csvFileReader;
    private CsvFileFinder csvFileFinder;
    private DbTableManager tableManager;

    private FinderView finderView;

    private FinderService finderService;

    public FinderController(DbTableManager tableManager, CsvFileParser csvFileReader, CsvFileFinder csvFileFinder, FinderService finderService,
            FinderView finderView) {
        this.csvFileReader = csvFileReader;
        this.csvFileFinder = csvFileFinder;
        this.finderService = finderService;
        this.finderView = finderView;
        this.tableManager = tableManager;
    }

    public void start() {
        Path selectedFile = selectFile();
        String fileName = selectedFile.getFileName().toString();
        fileName = fileName.replace(".csv", "");
        fileName = fileName.toLowerCase();

        List<Question> csvRowList = csvFileReader.readCsvFile(selectedFile);
        // finderService.saveQuestionList(csvRowList);

        // 새 파일 등록시
        tableManager.createTable(fileName);
        finderService.saveDynamicList(csvRowList, fileName);


    }

    private Path selectFile() {
        Path selectedFolder = executeFindFolder();
        Path selectedFile = executeFindFile(selectedFolder);

        return selectedFile;
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
