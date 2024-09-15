package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.view.AppView;

public abstract class AppController {

    protected AppView view;

    protected Scanner scanner;

    public AppController(AppView view) {
        this.view = view;
        this.scanner = new Scanner(System.in);
    }
    
    

}
