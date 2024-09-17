package org.snoflo.application;

import org.snoflo.datacenter.CsvFileReader;
import org.snoflo.datacenter.QuestionDataConverter;
import org.snoflo.function.FileFinder;
import org.snoflo.service.FinderService;
import org.snoflo.service.QuestionService;
import org.snoflo.view.FinderView;
import org.snoflo.view.MainView;
import org.snoflo.view.QuestionView;

public class FinderGenerator {

    private FileFinder fileFinder;
    private FinderService finderService;
    private FinderView finderView;


    private CsvFileReader csvFileReader;
    private QuestionDataConverter dataConverter;
    private QuestionService questionService;
    
    private MainView mainView;
    private QuestionView questionView;

	public AppGenerator(FileFinder fileFinder, FinderService finderService, FinderView finderView) {
		this.fileFinder = fileFinder;
		this.finderService = finderService;
		this.finderView = finderView;
	}


}
