package agh.ics.oop.map;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.options.MapVariant;
import agh.ics.oop.utils.Vector2d;

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
    public WorldMap() {
        this.lowerLeftBoundary = new Vector2d(0, 0);
        this.upperRightBoundary = new Vector2d(MAP_WIDTH-1, MAP_HEIGHT-1);
        this.generateFields();
    }

    private void addField(int x, int y) {
        Vector2d positionVector = new Vector2d(x, y);
        this.mapFields.put(positionVector, new MapField(positionVector));
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
            switch(MAP_VARIANT) {
                case KULA_ZIEMSKA:
                    newLocation = calculateKulaZiemskaMove(newLocation);
                    break;
                case PIEKIELNY_PORTAL:
                    animal.changeEnergy(-BREEDING_ENERGY);
                    newLocation = Vector2d.random(new Vector2d(MAP_WIDTH, MAP_HEIGHT));
                    break;
            }
        }
        mapFields.get(oldLocation).removeAnimal(animal);
        mapFields.get(newLocation).addAnimal(animal);
        return newLocation;
    }

    private Vector2d calculateKulaZiemskaMove(Vector2d newLocation) {
        int x = newLocation.x();
        int y = newLocation.y();
        if(x < 0) x = MAP_WIDTH-1;
        if(x >= MAP_WIDTH) x = 0;
        if(y < 0) y = MAP_HEIGHT-1;
        if(y >= MAP_HEIGHT) y = 0;
        return new Vector2d(x, y);
    }

    public void moveAnimals() {
        for(Animal animal : animals) {
            animal.move();
        }
    }

    public Map<Vector2d, MapField> getFields() {
        return mapFields;
    }

    public void ageAnimals() {
        for(Animal animal : animals) {
            animal.ageAnimal();
        }
    }

    //Tymczasowa funkcja
    public String generuj() {
        String wypisz = "";
        for(int x = 0; x < MAP_WIDTH; x++) {
            for(int y = 0; y < MAP_HEIGHT; y++) {
                MapField mf = this.mapFields.get(new Vector2d(x, y));
                if(!mf.generuj().equals("")) {
                    wypisz += mf.generuj() + "\n";
                }
            }
        }
        wypisz += "\n";
        return wypisz;
    }
}
