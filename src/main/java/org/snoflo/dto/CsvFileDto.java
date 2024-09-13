package org.snoflo.dto;

import java.util.List;

public class CsvFileDto {

    private String csvFileName;
    private List<String> csvFileList;

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public List<String> getCsvFileList() {
        return csvFileList;
    }

    public void setCsvFileList(List<String> csvFileList) {
        this.csvFileList = csvFileList;
    }

}
