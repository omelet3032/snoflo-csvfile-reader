package org.snoflo.service;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.dto.FileDto;

public interface FinderService {
    
    List<Path> getFolderList();

    List<Path> getFileList(Path selectedFolder);

    void sendDtoToRepository(FileDto dto);
}
