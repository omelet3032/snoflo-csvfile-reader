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
import org.snoflo.function.TableManager;
import org.snoflo.repository.FinderRepositoryImpl;
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
    // private TableManager tableManager;

    private FinderView finderView;

    private FinderService finderService;

    public FinderController(CsvFileParser csvFileReader, CsvFileFinder csvFileFinder, FinderService finderService,
            FinderView finderView) {
        this.csvFileReader = csvFileReader;
        this.csvFileFinder = csvFileFinder;
        this.finderService = finderService;
        this.finderView = finderView;
        // this.tableManager = tableManager;
    }
    /* 
     * finderController가 전해줘야 할 것은 String csvFileName
     */

    public void start() {
        Path selectedFile = selectFile();
        String fileName = selectedFile.getFileName().toString();
        fileName = fileName.replace(".csv", "");
        fileName = fileName.toLowerCase();

        List<Question> csvRowList = csvFileReader.readCsvFile(selectedFile);

        String tableName = finderService.checkQuestionTable(selectedFile);

        if (tableName.isBlank()) {
            finderService.createQuestionTable(selectedFile);
        } else {
            System.out.println("파일을 덮어씌우시겠습니까?");
            finderService.truncateQuestionTable(selectedFile);
        }
        finderService.saveQuestionList(csvRowList, selectedFile);

        // 새 파일 등록시
        // tableManager.createTable(fileName);

        /* 
         * 파일 등록하기 로직
         *  1. 사용자가 등록한 csv파일을 일단 List<Question>으로 변환
         *  2. 기존에 등록한 파일인지 확인
         *      등록한 파일이면? truncate -> save
         *      처음 등록한 파일이면? create
         */
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
