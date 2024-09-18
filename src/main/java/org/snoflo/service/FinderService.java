package org.snoflo.service;

import java.nio.file.Path;

import org.snoflo.repository.FinderRepository;

public class FinderService {

    private FinderRepository finderRepository;

    public FinderService (FinderRepository finderRepository) {
        this.finderRepository = finderRepository;
    }

    public void saveFile(Path selectedFile) {
        finderRepository.save(selectedFile);
    };
}
