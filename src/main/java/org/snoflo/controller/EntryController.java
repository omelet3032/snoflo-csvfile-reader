package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.strategy.AppStrategy;
import org.snoflo.strategy.AppContext;
import org.snoflo.view.EntryView;

public class EntryController {

    private AppStrategy fileManagerStrategy;

    private AppStrategy randomQuizStrategy;

    private Scanner scanner;

    private EntryView entryView;

    private AppContext context;

    public EntryController(
            AppContext context,
            AppStrategy fileManagerStrategy,
            AppStrategy randomQuizStrategy, 
            Scanner scanner, 
            EntryView entryView) {
        this.context = context;
        this.fileManagerStrategy = fileManagerStrategy;
        this.randomQuizStrategy = randomQuizStrategy;
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
                    context.runContext(randomQuizStrategy);
                    break;
                case "2":
                    context.runContext(fileManagerStrategy);
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
