package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.strategy.AppStrategy;
import org.snoflo.strategy.AppContext;
import org.snoflo.view.EntryView;

public class EntryController {

    private AppStrategy csvFileManagerCommander;

    private AppStrategy randomQuizCommander;

    private Scanner scanner;

    private EntryView entryView;

    private AppContext context;

    public EntryController(
            AppContext context,
            AppStrategy csvFileManagerCommander,
            AppStrategy randomQuizCommander, 
            Scanner scanner, 
            EntryView entryView) {
        this.context = context;
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
                    context.runContext(randomQuizCommander);
                    break;
                case "2":
                    context.runContext(csvFileManagerCommander);
                    break;
                case "3":
                    entryView.showAskExitApp();
                    entryView.showSelectYorN();

                    while (true) {

                        answer = scanner.nextLine();

                        if (answer.equals("Y")) {
                            entryView.showPromptExitApp();
                            return;
                        } else if (answer.equals("n")) {
                            entryView.showPromptReturnMainMenu();
                            break;
                        } else {
                            entryView.showSelectYorN();
                        }
                    }
                    break;
                default:
                    entryView.showPromptCorrectNumber();
            }
        }
    }

}
