package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.map.WorldMap;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println("Simulation started...");

        Application.launch(App.class, args);

        System.out.println("...Simulation ended");
    }
}
