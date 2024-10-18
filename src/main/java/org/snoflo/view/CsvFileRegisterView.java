package org.snoflo.view;


public class CsvFileRegisterView implements AppView {

    // public void showPromptFolder() {
    //     System.out.println("---------------------------------");
    //     System.out.println("폴더 등록");
    //     System.out.println("---------------------------------");
    // }

    // public void showSelectFolder(List<Path> folderList) {
    //     System.out.println("---------------------------------");
    //     for (int i = 0; i < folderList.size(); i++) {
    //         System.out.println(i + ". " + folderList.get(i));
    //     }
    // }

    // public void showPromptCsvFile() {
    //     System.out.println("---------------------------------");
    //     System.out.println("파일 등록");
    //     System.out.println("---------------------------------");
    // }

    // public void showSelectCsvFile(List<Path> csvFileList) {
    //     System.out.println("---------------------------------");
    //     for (int i = 0; i < csvFileList.size(); i++) {
    //         System.out.println(i + ". " + csvFileList.get(i));
    //     }
    // }
    
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
