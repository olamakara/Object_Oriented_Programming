package agh.ics.oop.map;

import agh.ics.oop.configurations.border.IBorderOption;
import agh.ics.oop.configurations.genes.IGeneOption;
import agh.ics.oop.elements.Animal;
import agh.ics.oop.options.MapVariant;
import agh.ics.oop.utils.Vector2d;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static agh.ics.oop.elements.Constants.*;

public class WorldMap {
    private final Map<Vector2d, MapField> mapFields = new HashMap<>();
    private final List<Animal> animals = new ArrayList<>();
    private final Vector2d lowerLeftBoundary;
    private final Vector2d upperRightBoundary;
    private final IBorderOption borderOption;
    private final IGeneOption geneOption;
    private final GridPane grid;

    public WorldMap(IBorderOption borderOption, IGeneOption geneOption, GridPane grid) {
        this.grid = grid;
        this.borderOption = borderOption;
        this.geneOption = geneOption;
        this.lowerLeftBoundary = new Vector2d(0, 0);
        this.upperRightBoundary = new Vector2d(MAP_WIDTH-1, MAP_HEIGHT-1);
        this.generateFields();
    }

    private void addField(int x, int y) {
        Vector2d positionVector = new Vector2d(x, y);
        this.mapFields.put(positionVector, new MapField(positionVector, grid, this, geneOption));
    }

    private void generateFields() {
        for(int x = 0; x < MAP_WIDTH; x++) {
            for(int y = 0; y < MAP_HEIGHT; y++) {
                addField(x, y);
            }
        }
    }

    public void addAnimal(Animal animal) {
        mapFields.get(animal.getLocation()).addAnimal(animal);
        animals.add(animal);
    }

    public Vector2d moveAnimal(Animal animal, Vector2d oldLocation, Vector2d newLocation) {
        if(!((newLocation.follows(lowerLeftBoundary)) && (newLocation.precedes(upperRightBoundary)))) {
            newLocation = borderOption.calculateLocationAfterBorderHit(newLocation, animal);
        }
        mapFields.get(oldLocation).removeAnimal(animal);
        mapFields.get(newLocation).addAnimal(animal);
        return newLocation;
    }

    public void moveAnimals() {
        for(Animal animal : animals) {
            animal.move();
        }
    }

    public void makeAnimalsEatGrass() {
        for(MapField mapField : mapFields.values()) {
            mapField.eatGrass();
        }
    }

    public void makeAnimalsReproduce() {
        for(MapField mapField : mapFields.values()) {
            mapField.reproduce();
        }
    }

    public void removeDeadAnimals() {
        for(Animal animal : animals) {
            if(animal.getEnergy() <= 0) {
                mapFields.get(animal.getLocation()).removeAnimal(animal);
            }
        }

        animals.removeIf(next -> next.getEnergy() <= 0);
    }

    public Map<Vector2d, MapField> getFields() {
        return mapFields;
    }

    public void dayGoneBy() {
        for(MapField mapField : mapFields.values()) {
            mapField.manageUI();
        }

        for(Animal animal : animals) {
            animal.ageAnimal();
        }
    }
}
