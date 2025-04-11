package com.example.arkitekturexempel;

import com.example.arkitekturexempel.controller.MainController;
import com.example.arkitekturexempel.database.Database;

public class Main {
    public static void main(String[] args) {

        Database.initialize();

        MainController maincontroller = new MainController();
        maincontroller.menu();

    }
}