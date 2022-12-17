package agh.ics.oop.gui;

import agh.ics.oop.Simulation;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static agh.ics.oop.elements.Constants.*;

public class AppInstance {
    private Scene scene;
    private Simulation simulation;
    private GridPane grid;

    public void init() {
        Stage stage = new Stage();
        stage.setScene(this.getScreen());
        stage.show();

        simulation = new Simulation(grid);
        simulation.setDelay(DAY_DELAY);

        Thread engineThread = new Thread(simulation);
        engineThread.start();

        stage.setOnHiding(event -> {
            simulation.isActive = false;
        });
    }

    public AppInstance() {
        this.generateScreen();
        this.init();
    }

    public Scene getScreen() {
        return this.scene;
    }

    private void generateScreen() {
        VBox vbox = new VBox(8);
        vbox.setAlignment(Pos.CENTER);

        generateGrid();
        vbox.getChildren().add(grid);

        this.scene = new Scene(vbox, MAP_WIDTH* UI_GRID_SIZE +50, MAP_HEIGHT* UI_GRID_SIZE +50);
    }

    private void generateGrid() {
        grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.CENTER);

        for(int i = 0; i < MAP_WIDTH; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(UI_GRID_SIZE));
        }

        for(int i = 0; i < MAP_HEIGHT; i++) {
            grid.getRowConstraints().add(new RowConstraints(UI_GRID_SIZE));
        }

        VBox vbox = new VBox(8);
        vbox.getChildren().add(grid);
    }
}
