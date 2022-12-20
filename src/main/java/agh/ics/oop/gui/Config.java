package agh.ics.oop.gui;

import agh.ics.oop.options.BehaviourVariant;
import agh.ics.oop.options.GeneVariant;
import agh.ics.oop.options.MapVariant;
import agh.ics.oop.options.PuszczaVariant;
import agh.ics.oop.utils.ConstantsConfig;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;

public class Config {
    private Scene scene;
    private Stage stage;
    private ConstantsConfig currentConfig;

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

            AppInstance appInstance = new AppInstance(currentConfig);
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
            try {
                getConstants(file);
            } catch (InputMismatchException | FileNotFoundException e) {
                System.out.print(e.getMessage());
            }
        });

        return importFile;
    }

    private void getConstants(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Map<String, Object> values = new HashMap<String, Object>();

        while(scanner.hasNext()) {
            String[] nextValue = scanner.next().split("=");
            switch (nextValue[0]) {
                case "MAP_VARIANT" -> values.put(nextValue[0], MapVariant.valueOf(nextValue[1]));
                case "PUSZCZA_VARIANT" -> values.put(nextValue[0], PuszczaVariant.valueOf(nextValue[1]));
                case "GENE_VARIANT" -> values.put(nextValue[0], GeneVariant.valueOf(nextValue[1]));
                case "GENE_JUMP_VARIANT" -> values.put(nextValue[0], BehaviourVariant.valueOf(nextValue[1]));
                case "GRIDLINES_VISIBLE" -> values.put(nextValue[0], parseBoolean(nextValue[1]));
                default -> values.put(nextValue[0], Integer.parseInt(nextValue[1]));
            }
        }

        this.currentConfig = new ConstantsConfig(new HashMap<String, Object>(values));
    }
}
