package org.snoflo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.snoflo.domain.Question;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileReader;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        CsvFileReader csvFileReader = new CsvFileReader();

        CsvFileFinder csvFileFinder = new CsvFileFinder();

        List<Path> folderList = csvFileFinder.getFolderList();
        System.out.println(folderList);

        System.out.println(folderList.get(4));

        List<Path> fileList = csvFileFinder.getFileList(folderList.get(4));

        Path selectedFile = fileList.get(0);

        // Path selectedFile = Paths.get("/home/omelet1/dev3/study-java/csvfilefindertest/csv/omelet2.csv");
        // Path selectedFile = Paths.get("/home/omelet1/dev3/study-java/csvfilefindertest/csv/omelet2.csv");
        List<Question> list = csvFileReader.readCsvFile(selectedFile);

        System.out.println();
        System.out.println("-------------");
        System.out.println("최종 list");
        for (Question question : list) {
            System.out.println(question.toString());
            System.out.println();
        }
    }
}
