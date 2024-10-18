package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.commander.AppCommander;
import org.snoflo.view.EntryView;

public class EntryController {

    private AppCommander csvFileManagerCommander;

    private AppCommander randomQuizCommander;

    private Scanner scanner;

    private EntryView entryView;

    public EntryController(
            AppCommander csvFileManagerCommander,
            AppCommander randomQuizCommander, Scanner scanner, EntryView entryView) {
        this.csvFileManagerCommander = csvFileManagerCommander;
        this.randomQuizCommander = randomQuizCommander;
        this.scanner = scanner;
        this.entryView = entryView;
    }

    public void start() {

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
                    entryView.showAskExitApp();
                    entryView.showPromptYorN();

                    while (true) {

                        answer = scanner.nextLine();

                        if (answer.equals("Y")) {
                            entryView.showPromptExitApp();
                            return;
                        } else if (answer.equals("n")) {
                            entryView.showPromptReturnMainMenu();
                            break;
                        } else {
                            entryView.showPromptYorN();
                        }
                    }
                    break;
                default:
                    entryView.showPromptCorrectNumber();
            }
        }
    }

}
