package agh.ics.oop.gui;

import agh.ics.oop.Simulation;
import agh.ics.oop.elements.Constants;
import agh.ics.oop.options.BehaviourVariant;
import agh.ics.oop.options.GeneVariant;
import agh.ics.oop.options.MapVariant;
import agh.ics.oop.options.PuszczaVariant;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static agh.ics.oop.elements.Constants.*;
import static java.lang.Boolean.parseBoolean;

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
            try {
                getConstants(file);
            } catch (InputMismatchException | FileNotFoundException e) {
                System.out.print(e.getMessage());
            }
        });

        return importFile;
    }

    private static void getConstants(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while(scanner.hasNext()) {
            MAP_WIDTH = scanner.nextInt();
            MAP_HEIGHT = scanner.nextInt();

            MAP_VARIANT = MapVariant.valueOf(scanner.next());

            START_GRASS_COUNT = scanner.nextInt();
            GRASS_EATEN_ENERGY = scanner.nextInt();
            DAILY_GRASS_COUNT = scanner.nextInt();

            PUSZCZA_VARIANT = PuszczaVariant.valueOf((scanner.next()));

            START_ANIMAL_COUNT = scanner.nextInt();
            START_ANIMAL_ENERGY = scanner.nextInt();
            FULL_ANIMAL_ENERGY = scanner.nextInt();
            BREEDING_ENERGY = scanner.nextInt();
            MINIMUM_MUTATIONS = scanner.nextInt();
            MAXIMUM_MUTATIONS = scanner.nextInt();

            GENE_VARIANT = GeneVariant.valueOf((scanner.next()));

            GENOME_LENGTH = scanner.nextInt();

            GENE_JUMP_VARIANT = BehaviourVariant.valueOf((scanner.next()));

            DAILY_ENERGY_DECREASE = scanner.nextInt();
            DAY_DELAY = scanner.nextInt();
            UI_GRID_SIZE = scanner.nextInt();
            UI_BOX_SIZE = scanner.nextInt();
            UI_ANIMAL_SIZE = scanner.nextInt();

            GRIDLINES_VISIBLE = parseBoolean(scanner.next());

            System.out.println("Files successfully uploaded");
        }
    }
}
