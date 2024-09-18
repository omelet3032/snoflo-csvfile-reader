package org.snoflo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.snoflo.repository.QuestionDataConverter;

/* 
 * 이 클래스가 관계를 맺어야 하는 클래스는 CsvFileDto와 AppView
 * 그리고 Service클래스 Controller 클래스
 */
public class FinderServiceImpl implements FinderService {

    private QuestionDataConverter dataConverter;

    public FinderServiceImpl (QuestionDataConverter dataConverter) {
        this.dataConverter = dataConverter;
    }

	@Override
	public void sendSelectedFile(Path selectedFile) {
        dataConverter.convertDataForDomain(selectedFile);
	}


}
