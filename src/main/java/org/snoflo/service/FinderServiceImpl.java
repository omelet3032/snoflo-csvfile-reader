package org.snoflo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.snoflo.dto.FileDto;
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

    // 전달받은 Path객체를 Dto에 삽입하는 비즈니스 로직은 서비스에서 정의해야 한다.


    // 주입받은 dto를 반환하는 메서드 (dataceonverter 클래스로 보내기용)
    @Override
    public List<Path> getFolderList() {
        Path dirPath = Paths.get(System.getProperty("user.dir"));
        int maxDepth = 2;

        try {
            return Files.walk(dirPath, maxDepth).filter(Files::isDirectory).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public List<Path> getFileList(Path selectedFolder) {

        try {
            return Files.list(selectedFolder)
                    .filter(file -> Files.isRegularFile(file))
                    .filter(path -> path.toString().endsWith(".csv"))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

	@Override
	public void sendDtoToRepository(FileDto dto) {
        dataConverter.processDto(dto);
	}


}
