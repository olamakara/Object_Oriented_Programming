package agh.ics.oop.gui;

import agh.ics.oop.Simulation;
import agh.ics.oop.utils.ConstantsConfig;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppInstance {
    private Scene scene;
    private Simulation simulation;
    private final ConstantsConfig currentConfig;
    private GridPane grid;
    private final int width;
    private final int height;
    private final int gridSize;

    public void init() {
        Stage stage = new Stage();
        stage.setScene(this.getScreen());
        stage.show();

        simulation = new Simulation(grid, currentConfig);
        simulation.setDelay(currentConfig.getInt("DAY_DELAY"));

        Thread engineThread = new Thread(simulation);
        engineThread.start();

        stage.setOnHiding(event -> {
            simulation.isActive = false;
        });
    }

    public AppInstance(ConstantsConfig currentConfig) {
        this.currentConfig = currentConfig;
        width = currentConfig.getInt("MAP_WIDTH");
        height = currentConfig.getInt("MAP_HEIGHT");
        gridSize = currentConfig.getInt("UI_GRID_SIZE");
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

        this.scene = new Scene(vbox, width * gridSize +50, height * gridSize +50);
    }

    private void generateGrid() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.setGridLinesVisible(currentConfig.getBool("GRIDLINES_VISIBLE"));

        for(int i = 0; i < width; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(gridSize));
        }

        for(int i = 0; i < height; i++) {
            grid.getRowConstraints().add(new RowConstraints(gridSize));
        }

        VBox vbox = new VBox(8);
        vbox.getChildren().add(grid);
    }
}
