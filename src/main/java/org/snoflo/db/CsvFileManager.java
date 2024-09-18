package org.snoflo.db;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.domain.Question;

public class CsvFileManager {

    private CsvFileReader csvFileReader;
    private CsvFileConverter csvFileConverter;

    public CsvFileManager() {
    }

    public void saveToDb(Path selectedFile) {
        csvFileReader = new CsvFileReader();
        List<String[]> rowList = csvFileReader.readCsvFile(selectedFile);

        // 1. rowList 추출
        // 2. 쿼리 작성 "insert into "
    }


    // db 연결전까지 임시 메서드
    public List<Question> generateData(Path selectedFile) {
        csvFileReader = new CsvFileReader();
        csvFileConverter = new CsvFileConverter();        
        List<String[]> rowList = csvFileReader.readCsvFile(selectedFile);
        List<Question> questionList = csvFileConverter.convertData(rowList); // 여기서 유효성 체크하지 않고.. Question에서..
        Question question =  new Question();
        question.addData(rowList);
        
        return questionList;         
    }
}
