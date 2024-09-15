package org.snoflo.view;

import java.nio.file.Path;
import java.util.List;

public class FinderView extends AppView {

    private List<Path> folderList;
    private List<Path> fileList;

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
    }

    public void showPromptCsvFile() {
        System.out.println("---------------------------------");
        System.out.println("파일 등록");
        System.out.println("---------------------------------");
    }

    public void showSelectCsvFile(List<Path> csvFileList) {
        System.out.println("---------------------------------");
        for (int i = 0; i < csvFileList.size(); i++) {
            System.out.println(i + ". " + csvFileList.get(i));
        }
    }

}
