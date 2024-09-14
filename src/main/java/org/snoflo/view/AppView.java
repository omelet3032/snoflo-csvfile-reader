package org.snoflo.view;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.model.Question;

public class AppView {

    public void showPromptMainMenu() {
        System.out.println("---------------------------------");
        System.out.println("퀴즈 시작");
        System.out.println("---------------------------------");
    }

    public void showPromptFolder() {
        System.out.println("---------------------------------");
        System.out.println("폴더 등록");
        System.out.println("---------------------------------");
    }

    public void showSelectFolder(List<Path> folderList) {
        System.out.println("---------------------------------");
        for (int i = 0; i < folderList.size(); i++) {
            System.out.println(i + ". " + folderList.get(i));
        }
        System.out.println("---------------------------------");
    }

    public void showPromptCsvFile() {
        System.out.println("---------------------------------");
        System.out.println("csv 파일 등록");
        System.out.println("---------------------------------");
    }

    public void showSelectCsvFile(List<Path> csvFileList) {
        System.out.println("---------------------------------");
        for (int i = 0; i < csvFileList.size(); i++) {
            System.out.println(i + ". " + csvFileList.get(i));
        }
        System.out.println("---------------------------------");
    }

    public void showSelectMenu() {
        System.out.println("--------------------------------");
        System.out.println("메뉴 선택");
        System.out.println("1. 전체 출력");
        System.out.println("2. id로 검색");
        System.out.println("---------------------------------");
    }

    public void showResultFindAll(List<Question> list) {
        System.out.println("--------------------------------");
        for (Question question : list) {
            System.out.println(question.toString());
        }
        System.out.println("--------------------------------");
    }

    public void showPromptFindById() {
        System.out.println("-----------------------------");
        System.out.println("----- id로 검색하기 -----");
        System.out.println("-----------------------------");
    }

    public void showResultFindById(Question concept) {
        System.out.println();
        System.out.println("conceptById : " + concept);
    }

}
