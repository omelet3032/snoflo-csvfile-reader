package org.snoflo.db;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.dbconnection.MysqlConnection;
import org.snoflo.domain.Question;

public class CsvFileManager {

    private static CsvFileReader csvFileReader;
    private static CsvFileConverter csvFileConverter;

    private static List<Question> questionList;
    
    public static void saveToDb(Path selectedFile) {
        csvFileReader = new CsvFileReader();
        csvFileConverter = new CsvFileConverter();        
        List<String[]> rowList = csvFileReader.readCsvFile(selectedFile);
        questionList = csvFileConverter.convertData(rowList); 
        
        // 1. rowList 추출
        // 2. 쿼리 작성 "insert into "

        MysqlConnection.getInstance();
        
        
    }
    
    
    // db 연결전까지 임시 메서드
    public List<Question> generateData(Path selectedFile) {
        csvFileReader = new CsvFileReader();
        csvFileConverter = new CsvFileConverter();        
        List<String[]> rowList = csvFileReader.readCsvFile(selectedFile);
        this.questionList = csvFileConverter.convertData(rowList); // 여기서 유효성 체크하지 않고.. Question에서..
        // Question question =  new Question();
        // question.addData(rowList);
        
        return this.questionList;         
    }
    
    public List<Question> getQuestionList() {
        return this.questionList;
    }
}
