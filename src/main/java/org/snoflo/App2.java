package org.snoflo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.snoflo.domain.Question;
import org.snoflo.function.CsvFileFinder;
import org.snoflo.function.CsvFileReader;
import org.snoflo.function.RandomQuestion;

/**
 * Hello world!
 *
 */
public class App2 {
    public static void main(String[] args) {

        CsvFileReader csvFileReader = new CsvFileReader();
        CsvFileFinder csvFileFinder = new CsvFileFinder();

        List<Path> folderList = csvFileFinder.getFolderList();
        List<Path> fileList = csvFileFinder.getFileList(folderList.get(4));

        Path selectedFile = fileList.get(0);

        List<Question> list = csvFileReader.readCsvFile(selectedFile);

        // for (Question question : list) {
        //     System.out.println(question.toString());
        //     System.out.println();
        // }
        
        RandomQuestion randomQuestion = new RandomQuestion();

        System.out.println();
        System.out.println("playquiz 시작");
        System.out.println();
        System.out.println("while전 list : " + list.size());
        System.out.println("wile전 list : " +"\n" + list + "\n");
        
        randomQuestion.playRandomQuiz(list);
        // while(!list.isEmpty()) {

        //     Question question = randomQuestion.getRandomElement(list);
        //     System.out.println("랜덤 출력된 question : " + question.toString());
        //     System.out.println();
        //     System.out.println("list의 size : " + list.size());
        //     System.out.println();
    
        //     list = randomQuestion.removeQuestion(list, question);
            
        //     // for (Question question2 : list) {
        //     //     System.out.println(question2.toString());
        //     //     System.out.println();
        //     // }
        //     // System.out.println("랜덤 출력후 list size " + list.size());
        // }

        System.out.println("최종 list 사이즈 : " + list.size());
        System.out.println("최종 list : " + list);
    }

    
}
