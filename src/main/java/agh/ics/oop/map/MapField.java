package agh.ics.oop.map;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.utils.Vector2d;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import static agh.ics.oop.elements.Constants.*;

public class MapField {
    private final Vector2d position;
    private final GridPane grid;

    private Rectangle grassRect;
    private Rectangle animalRect;
    private IGrassGenObserver grassGenObserver;

    private final TreeSet<Animal> animals = new TreeSet<Animal>(new Comparator<Animal>() {
        @Override
        public int compare(Animal animal1, Animal animal2) {
            if(animal1.getEnergy() != animal2.getEnergy()) {
                return animal2.getEnergy() - animal1.getEnergy();
            }
            if(animal1.getAge() != animal2.getAge()) {
                return animal2.getAge() - animal1.getAge();
            }
            if(animal1.getChildrenCount() != animal2.getChildrenCount()) {
                return animal2.getChildrenCount() - animal1.getChildrenCount();
            }
            return 0;
        }
    });
    private boolean isGrass = false;

    public MapField(Vector2d position, GridPane grid) {
        this.grid = grid;
        this.position = position;
        Platform.runLater(this::generateUIElements);
    }

    private void generateUIElements() {
        grassRect = new Rectangle(UI_BOX_SIZE, UI_BOX_SIZE);
        grassRect.setFill(Color.FORESTGREEN);
        grassRect.setStroke(Color.FORESTGREEN);
        grassRect.setVisible(this.isGrass);
        grid.add(grassRect, position.x(), position.y());
        GridPane.setHalignment(grassRect, HPos.CENTER);

        animalRect = new Rectangle(UI_ANIMAL_SIZE, UI_ANIMAL_SIZE);
        animalRect.setFill(Color.color(0.8, 0, 0));
        animalRect.setStroke(Color.color(0.8, 0, 0));
        animalRect.setVisible(false);
        grid.add(animalRect, position.x(), position.y());
        GridPane.setHalignment(animalRect, HPos.CENTER);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addGrassGenObserver(IGrassGenObserver observer) {
        this.grassGenObserver = observer;
    }

    public void eatGrass() {
        if(this.isGrass && animals.size() != 0) {
            animals.first().addEnergy(GRASS_EATEN_ENERGY);
            this.isGrass = false;
            this.grassGenObserver.freeGrass(this.position);
        }
    }

    public void manageUI() {
        if(animalRect == null || grassRect == null) return;
        grassRect.setVisible(this.isGrass);
        if(animals.size() == 0) animalRect.setVisible(false);
        else {
            animalRect.setOpacity(Math.min(1, (double)animals.first().getEnergy()/FULL_ANIMAL_ENERGY));
            animalRect.setVisible(true);
        }
    }
    public void setGrass(boolean newState) {
        this.isGrass = newState;
    }
}
