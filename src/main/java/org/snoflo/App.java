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

        CsvFileReader csvFileReader = new CsvFileReader(new Question());
        CsvFileFinder csvFileFinder = new CsvFileFinder();

        List<Path> folderList = csvFileFinder.getFolderList();
        List<Path> fileList = csvFileFinder.getFileList(folderList.get(4));

        Path selectedFile = fileList.get(0);

        List<Question> list = csvFileReader.readCsvFile(selectedFile);

        for (Question question : list) {
            System.out.println(question.toString());
            System.out.println();
        }
    }
}
