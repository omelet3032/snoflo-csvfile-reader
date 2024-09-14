package org.snoflo.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.snoflo.dto.CsvFileDto;
import org.snoflo.model.Question;
import org.snoflo.repository.QuestionDataConverter;
import org.snoflo.service.CsvFilesFinderService;
import org.snoflo.service.QuestionService;
import org.snoflo.service.QuestionServiceImpl;
import org.snoflo.view.AppView;

public class QuestionController extends AppController {

    private QuestionService appService;

    public QuestionController(QuestionService appService) {

        // 이 시점에서 dto는 값을 가지게 된다.
        // DataConverter 클래스는 QuestionService가 실행되면서 동시에 실행된다.
        //dto가 필요한 클래스는 DataConverter 클래스와 Reader 클래스다.
        // 
        // this.appService = new QuestionServiceImpl(csvFileDto);
        this.appService = appService;
        executeMainMenu();
    }

    private void executeMainMenu() {
        view.showPromptMainMenu();
        view.showSelectMenu();
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1 -> executeFindAll();
            default -> executeMainMenu();
        }
    }

    // QuestionService의 메서드 시작
    private void executeFindById() {
        view.showPromptFindById();
        int id = scanner.nextInt();
        scanner.nextLine();
        Question conceptById = appService.findConceptById(id);
        view.showResultFindById(conceptById);
    }

    private void executeFindAll() {
        List<Question> list = appService.findAll();
        view.showResultFindAll(list);
    }



}
