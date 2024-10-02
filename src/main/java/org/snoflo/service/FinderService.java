package org.snoflo.service;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.snoflo.domain.Question;
import org.snoflo.repository.FinderRepository;

public class FinderService {

	private FinderRepository finderRepository;

	public FinderService(FinderRepository finderRepository) {
		this.finderRepository = finderRepository;
	}

	public void saveQuestionList(List<Question> csvRowList) {
		finderRepository.save(csvRowList);
	}

	
}