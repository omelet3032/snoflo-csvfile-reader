package org.snoflo.controller;

import java.util.Scanner;

import org.snoflo.view.AppView;

public abstract class AppController {

    protected AppView view = new AppView();

    protected Scanner scanner = new Scanner(System.in);
    

}
