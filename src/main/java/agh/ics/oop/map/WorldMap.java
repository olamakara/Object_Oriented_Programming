package agh.ics.oop.map;

import agh.ics.oop.configurations.border.IBorderOption;
import agh.ics.oop.configurations.behaviour.IBehaviourOption;
import agh.ics.oop.configurations.genes.IGeneOption;
import agh.ics.oop.configurations.puszcza.IPuszczaOption;
import agh.ics.oop.elements.Animal;
import agh.ics.oop.utils.ConstantsConfig;
import agh.ics.oop.utils.Vector2d;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMap {
    private final Map<Vector2d, MapField> mapFields = new HashMap<>();
    private final List<Animal> animals = new ArrayList<>();
    private final Vector2d lowerLeftBoundary;
    private final Vector2d upperRightBoundary;
    private final IBorderOption borderOption;
    private final IGeneOption geneOption;
    private final IBehaviourOption behaviourOption;
    private final IPuszczaOption puszczaOption;
    private final GridPane grid;
    private final int width;
    private final int height;
    private final ConstantsConfig currentConfig;

    public WorldMap(IBorderOption borderOption, IGeneOption geneOption, IBehaviourOption behaviourOption, IPuszczaOption puszczaOption, GridPane grid, ConstantsConfig currentConfig) {
        this.grid = grid;
        this.currentConfig = currentConfig;
        this.borderOption = borderOption;
        this.geneOption = geneOption;
        this.behaviourOption = behaviourOption;
        this.puszczaOption = puszczaOption;
        this.width = currentConfig.getInt("MAP_WIDTH");
        this.height = currentConfig.getInt("MAP_HEIGHT");
        this.lowerLeftBoundary = new Vector2d(0, 0);
        this.upperRightBoundary = new Vector2d(this.width-1, this.height-1);
        this.generateFields();
    }

    private void addField(int x, int y) {
        Vector2d positionVector = new Vector2d(x, y);
        this.mapFields.put(positionVector, new MapField(positionVector, grid, this, geneOption, behaviourOption, puszczaOption, currentConfig));
    }

    private void generateFields() {
        for(int x = 0; x < this.width; x++) {
            for(int y = 0; y < this.height; y++) {
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
            newLocation = borderOption.calculateLocationAfterBorderHit(newLocation, animal, width, height, currentConfig.getInt("BREEDING_ENERGY"));
        }
        mapFields.get(oldLocation).removeAnimal(animal, false);
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
                mapFields.get(animal.getLocation()).removeAnimal(animal, true);
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
