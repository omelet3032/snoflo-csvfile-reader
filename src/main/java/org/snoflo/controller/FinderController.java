package org.snoflo.controller;

import java.nio.file.Path;
import java.util.List;

import org.snoflo.dto.FileDto;
import org.snoflo.service.FinderService;
import org.snoflo.view.FinderView;

// csvFile을 세팅하는 도메인 컨트롤러
public class FinderController extends AppController {

    private FinderService finderService;
    private FinderView finderView;

    public FinderController(FinderService finderService, FinderView finderView) {
        this.finderService = finderService;
        this.finderView = finderView;
        sendDtoToService();
    }

    public void sendDtoToService () {
        FileDto dto = selectFile();
        finderService.sendDtoToRepository(dto);
    }


    public FileDto selectFile() {
        Path selectedFolder = executeFindFolder();
        Path selectedFile = executeFindCsvFile(selectedFolder);

        FileDto fileDto = new FileDto(selectedFile);

        return fileDto;
    }

    private Path executeFindFolder() {
        finderView.showPromptFolder();

        List<Path> folderList = finderService.getFolderList();

        finderView.showSelectFolder(folderList);
        int number = scanner.nextInt();
        scanner.nextLine();

        Path selectedFolder = folderList.get(number);
        return selectedFolder;
    }

    private Path executeFindCsvFile(Path selectedFolder) {
        finderView.showPromptCsvFile();
        List<Path> fileList = finderService.getFileList(selectedFolder);

        finderView.showSelectCsvFile(fileList);
        int number = scanner.nextInt();
        scanner.nextLine();
        Path selectedFile = fileList.get(number);
        return selectedFile;
    }

}
