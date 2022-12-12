package agh.ics.oop.map;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.utils.Vector2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMap {
    private final Map<Vector2d, MapField> mapFields = new HashMap<>();
    private final List<Animal> animals = new ArrayList<>();
    public final int width;
    public final int height;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.generateFields();
    }

    private void addField(int x, int y) {
        Vector2d positionVector = new Vector2d(x, y);
        this.mapFields.put(positionVector, new MapField(positionVector));
    }

    private void generateFields() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                addField(x, y);
            }
        }
    }

    public void addAnimal(Animal animal) {
        mapFields.get(animal.getLocation()).addAnimal(animal);
        animals.add(animal);
    }

    public void moveAnimal(Animal animal, Vector2d oldLocation, Vector2d newLocation) {
        mapFields.get(oldLocation).removeAnimal(animal);
        mapFields.get(newLocation).addAnimal(animal);
    }

    public void moveAnimals() {
        for(Animal animal : animals) {
            animal.move();
        }
    }

    //Tymczasowa funkcja
    public String generuj() {
        String wypisz = "";
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
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
