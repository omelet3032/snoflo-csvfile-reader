package org.snoflo.dto;

import java.nio.file.Path;

public record CsvFileDto(Path folderName, String fileName) {
}

// // record 적용
// // csvFile은 고정이기 때문에 불변 객체s