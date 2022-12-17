package agh.ics.oop.gui;

import agh.ics.oop.Simulation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static agh.ics.oop.elements.Constants.MAP_HEIGHT;
import static agh.ics.oop.elements.Constants.MAP_WIDTH;

public class Config {
    private Scene scene;

    public Config() {
        this.generateScreen();
    }

    public Scene getScreen() {
        return this.scene;
    }

    private void generateScreen() {
        VBox vbox = new VBox(8);
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().add(getRunButton());

        this.scene = new Scene(vbox, 150, 150);
    }

    private Button getRunButton() {
        Button start = new Button("Uruchom symulacje");
        start.setOnAction(click -> {
            System.out.println("Wystartowano symulację...");

            AppInstance appInstance = new AppInstance();
        });

        return start;
    }
}
