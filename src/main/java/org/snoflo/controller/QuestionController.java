package org.snoflo.controller;

import java.io.IOException;
import java.util.Scanner;

import org.snoflo.dto.CsvFileDto;
import org.snoflo.dto.CsvFilesFinder;
import org.snoflo.model.Question;
import org.snoflo.repository.QuestionDataConverter;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.AppView;

public class QuestionController extends AppController {

    private QuestionService appService;
    private CsvFileDto csvFileDto;

    public QuestionController() {
        this.view = new AppView();
        this.scanner = new Scanner(System.in);

        this.appService = new QuestionServiceImpl();
        executeMainMenu();
    }

    private void executeMainMenu() {
        view.showPromptMainMenu();
        view.showSelectMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1 -> executeFindCsvFiles();
            case 2 -> executeFindById();
            default -> executeMainMenu();
        }
    }

    private void executeFindCsvFiles() {
        view.showPromptCsvFile();
        view.showSelectCsvFile(appService.findCsvFiles());
        int number = scanner.nextInt();
        scanner.nextLine();
        String selectedFile = appService.findCsvFiles().get(number);
        System.out.println("선택한 파일 : " + selectedFile.toString()); 

        csvFileDto = new CsvFileDto();
        csvFileDto.setCsvFileName(selectedFile);
        appService.setCsvFileDto(csvFileDto);
    }

    private void executeFindById() {
        view.showPromptFindById();
        int id = scanner.nextInt();
        scanner.nextLine();
        Question conceptById = appService.findConceptById(id);
        view.showResultFindById(conceptById);
    }

}
