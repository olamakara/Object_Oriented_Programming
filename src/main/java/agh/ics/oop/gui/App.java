package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.HPos;

import static agh.ics.oop.elements.Constants.DAY_DELAY;

public class App extends Application implements IAppObserver {

    private final GridPane gridPane = new GridPane();
    private Simulation simulation;

    public void init() {
        simulation = new Simulation();
        simulation.setDelay(DAY_DELAY);
        simulation.addObserver(this);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();

        Button start = new Button("Start");
        TextField moves = new TextField();
        VBox top = new VBox(moves, start);
        VBox screen = new VBox(top, gridPane);
        HBox body = new HBox(screen);
        start.setOnAction(click -> {
            Thread engineThread = new Thread(simulation);
            engineThread.start();
        });

        grid.setGridLinesVisible(true);
        Scene scene = new Scene(body, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void addText(int number) {
        Label label = new Label(Integer.toString(number));
        gridPane.add(label, 0, number, 1, 1);
    }

    @Override
    public void positionChanged(int number) {
        Platform.runLater(() -> {
            addText(number);
        });
    }
}
