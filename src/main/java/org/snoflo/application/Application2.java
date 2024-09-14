// package org.snoflo.application;

// import org.snoflo.controller.AppController;

// public class Application2 {

//     private AppController appController;
//     private static Application2 instance;

//     private Application2(AppController appController) {
//         this.appController = appController;
//     }

//     public static synchronized Application2 start(AppController appController) {
//         if (instance == null) {
//             instance = new Application2(appController);
//         }
//         return instance;
//     }

// }
