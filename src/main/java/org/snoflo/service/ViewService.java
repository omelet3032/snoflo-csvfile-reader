package org.snoflo.service;

import java.util.List;

import org.snoflo.model.Book;

public class ViewService {

    public void showMenuFindByAuthor() {
        System.out.println("-----------------------------");
        System.out.println("---- Author로 검색하기 -----");
        System.out.println("-----------------------------");
    }

    public void showResultFindByAuthor(List<Book> list) {
        System.out.println();
        System.out.println("listByAuthor : " + list);
    }

    public void showFirstScreen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showFirstScreen'");
    }

    public void showResultFirstScreen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showResultFirstScreen'");
    }

    public void showMainDisplay() {
        System.out.println("---------------------------------");
        System.out.println("도서관리 프로그램 시작");
        System.out.println("---------------------------------");
    }

    public void showSelectDisplayIfFileNotExist() {
        System.out.println("1. 파일 생성");
    }

    public void showSelectDisplay() {
        System.out.println("---------------------------------");
        System.out.println("메뉴 선택");
        System.out.println("1. 파일 생성");
        System.out.println("2. 작가명으로 검색");
        System.out.println("---------------------------------");
    }

}
