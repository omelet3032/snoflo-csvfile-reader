package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.application.ResourceHandler;
import org.snoflo.commander.AppCommander;
import org.snoflo.view.EntryView;

public class EntryController {

    private AppCommander csvFileManagerCommander;

    private AppCommander randomQuizCommander;


    private Scanner scanner;

    private ResourceHandler resourceHandler;

    public EntryController(ResourceHandler resourceHandler,
            AppCommander csvFileManagerCommander,
            AppCommander randomQuizCommander, Scanner scanner) {
        this.resourceHandler = resourceHandler;
        this.csvFileManagerCommander = csvFileManagerCommander;
        this.randomQuizCommander = randomQuizCommander;
        this.scanner = scanner;
    }

    public void start() {

        // resourceHandler.connectH2WebConsole();
        EntryView entryView = new EntryView();

        String answer;
        
        while (true) {
            entryView.showPromptMainMenu();
            entryView.showSelectStartMenu();
            answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    randomQuizCommander.executeCommander();
                    break;
                case "2":
                    csvFileManagerCommander.executeCommander();
                    break;
                case "3":
                    // exitApp();
                    entryView.showPromptExitApp();
                    return;
                default:
                    entryView.showPromptCorrectNumber();
            }
        }
    }

    // private void exitApp() {
    //     entryView.showPromptExitApp();
    //     resourceHandler.closeResource();
    //     System.exit(0);
    // }

}
