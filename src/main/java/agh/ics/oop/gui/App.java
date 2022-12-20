package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.geometry.HPos;

public class App extends Application {

    private final GridPane gridPane = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        Config config = new Config(primaryStage);
        primaryStage.setScene(config.getScreen());
        primaryStage.show();

    }
    public void addText(int number) {
        Label label = new Label(Integer.toString(number));
        gridPane.add(label, 0, number, 1, 1);
    }
}
