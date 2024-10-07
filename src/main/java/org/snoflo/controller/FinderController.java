package org.snoflo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.snoflo.db.TableCreator;
import org.snoflo.domain.Question;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileReader;
import org.snoflo.repository.FinderRepository;
import org.snoflo.service.FinderService;
import org.snoflo.view.FinderView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// csvFile을 세팅하는 도메인 컨트롤러
// file 선택후 save 메서드로 db에 csvfile을 저장하는 책임

public class FinderController extends AppController implements MainController {

    private CsvFileReader csvFileReader;
    private CsvFileFinder csvFileFinder;

    private FinderView finderView;

    private FinderService finderService;

    public FinderController(CsvFileReader csvFileReader, CsvFileFinder csvFileFinder, FinderService finderService,
            FinderView finderView) {
        this.csvFileReader = csvFileReader;
        this.csvFileFinder = csvFileFinder;
        this.finderService = finderService;
        this.finderView = finderView;
    }

    public void start() {
        Path selectedFile = selectFile();
        String fileName = selectedFile.getFileName().toString();
        fileName = fileName.replace(".csv", "");
        fileName = fileName.toLowerCase();

        List<Question> csvRowList = csvFileReader.readCsvFile(selectedFile);
        // finderService.saveQuestionList(csvRowList);
        HikariConfig config = new HikariConfig("/application-h2.properties");
        HikariDataSource dataSource = new HikariDataSource(config);

        TableCreator tableCreator = new TableCreator(dataSource);

        //기존 파일 수정시
                

        // 새 파일 등록시
        tableCreator.createTable(fileName);
        finderService.saveDynamicList(csvRowList, fileName);

    }

    public void registerFile() {
        Path selectedFile = selectFile();
        String fileName = selectedFile.getFileName().toString();
        fileName = fileName.replace(".csv", "");
        fileName = fileName.toLowerCase();
        
        List<Question> csvRowList = csvFileReader.readCsvFile(selectedFile);

        HikariConfig config = new HikariConfig("/application-h2.properties");
        HikariDataSource dataSource = new HikariDataSource(config);

        TableCreator tableCreator = new TableCreator(dataSource);
        tableCreator.createTable(fileName);

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
