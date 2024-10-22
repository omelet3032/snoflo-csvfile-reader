package org.snoflo.view;


public class FileRegisterView implements AppView {

    public void showPromptRegisterFile(String fileName) {
        System.out.println("---------------------------------");
        System.out.println(fileName + ".csv를 새로 등록합니다.");
    }
    
    public void showSelectOverwriteFile(String fileName) {
        System.out.println("---------------------------------");
        System.out.println(fileName + ".csv가 이미 등록되어 있습니다.");
        System.out.println("덮어씌우시겠습니까?");
        System.out.println("Y/n");
    }

    public void showPromptSaveCsvFileToDatabase(String csvFileName) {
        System.out.println("데이터베이스에 " + csvFileName + "을 저장하였습니다.");
    }
}
