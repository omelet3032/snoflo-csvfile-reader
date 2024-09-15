package org.snoflo.controller;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.dto.FileDto;
import org.snoflo.service.FinderService;
import org.snoflo.view.FinderView;
import org.snoflo.function.FileFinder;

// csvFile을 세팅하는 도메인 컨트롤러
public class FinderController extends AppController {

    private FileFinder fileFinder;
    private FinderService finderService;
    private FinderView finderView;

    public FinderController(FileFinder fileFinder, FinderService finderService, FinderView finderView) {
        this.fileFinder = fileFinder;
        this.finderService = finderService;
        this.finderView = finderView;
        // sendDtoToService();
    }

    public void sendDtoToService () {
        FileDto dto = selectFile();
        finderService.sendDtoToRepository(dto);
    }

    private FileDto selectFile() {
        Path selectedFolder = executeFindFolder();
        Path selectedFile = executeFindFile(selectedFolder);

        FileDto fileDto = new FileDto(selectedFile);

        return fileDto;
    }

    private Path executeFindFolder() {
        finderView.showPromptFolder();

        List<Path> folderList = fileFinder.getFolderList();

        finderView.showSelectFolder(folderList);
        int number = scanner.nextInt();
        scanner.nextLine();

        Path selectedFolder = folderList.get(number);
        return selectedFolder;
    }

    private Path executeFindFile(Path selectedFolder) {
        finderView.showPromptCsvFile();

        List<Path> fileList = fileFinder.getFileList(selectedFolder);

        finderView.showSelectCsvFile(fileList);
        int number = scanner.nextInt();
        scanner.nextLine();
        
        Path selectedFile = fileList.get(number);
        return selectedFile;
    }

}
