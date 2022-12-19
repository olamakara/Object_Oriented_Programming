package agh.ics.oop.gui;

import agh.ics.oop.Simulation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static agh.ics.oop.elements.Constants.MAP_HEIGHT;
import static agh.ics.oop.elements.Constants.MAP_WIDTH;

public class Config {
    private Scene scene;
    private Stage stage;

    public Config(Stage primaryStage) {
        this.stage = primaryStage;
        this.generateScreen();
    }

    public Scene getScreen() {
        return this.scene;
    }

    private void generateScreen() {
        VBox vbox = new VBox(8);
        vbox.setAlignment(Pos.CENTER);


        vbox.getChildren().add(getImportButton());
        vbox.getChildren().add(getRunButton());

        this.scene = new Scene(vbox, 250, 150);
    }

    private Button getRunButton() {
        Button start = new Button("Uruchom symulacje");
        start.setPrefWidth(150);
        start.setOnAction(click -> {
            System.out.println("Wystartowano symulację...");

            AppInstance appInstance = new AppInstance();
        });

        return start;
    }

    private Button getImportButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        Button importFile = new Button("Wgraj plik");
        importFile.setPrefWidth(150);
        importFile.setOnAction(click -> {
            File file = fileChooser.showOpenDialog(stage);
            System.out.println(file.getName());
        });

        return importFile;
    }
}
