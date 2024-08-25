package org.snoflo.library;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class BookDataGenerator {

    private final String CSV_FILE = "src/library.csv";

    private final Random random;

    private final String[] FIRST_NAMES = { "유", "김", "이", "박", "최", "나", "강", "국" };
    private final String[] LAST_NAMES = { "진우", "종철", "승현", "가영", "나영", "다영", "영호", "철수", "미진", "영수", "훈영", "선영", "태연",
            "설화" };

    public BookDataGenerator() {
        this.random = new Random();
    }

    public void generateCSV(int row) throws IOException {

        if (!isCSVExists(CSV_FILE)) {
            System.out.println("파일을 생성하였습니다.");
            createCSVFile(CSV_FILE, row);
        } else {
            System.out.println("이미 CSV파일이 생성되었습니다.");
        }

    }

    private void createCSVFile(String filename, int count) throws IOException {

        if (!isCSVExists(filename)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write("ISBN, Title, Author, Year\n");

                for (int i = 0; i < count; i++) {
                    String isbn = UUID.randomUUID().toString().substring(0, 13);
                    String title = "Book" + random.nextInt(count);
                    String author = generateRandomAuthor();
                    int year = 1950 + random.nextInt(74);
                    writer.write(String.format("%s,%s,%s,%d\n", isbn, title, author, year));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("파일이 이미 존재합니다.");
        }

    }

    private String generateRandomAuthor() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + lastName;
    }

    private boolean isCSVExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    public String getCSV_FILE() {
        return CSV_FILE;
    }

    public Random getRandom() {
        return random;
    }

    public String[] getFIRST_NAMES() {
        return FIRST_NAMES;
    }

    public String[] getLAST_NAMES() {
        return LAST_NAMES;
    }

}