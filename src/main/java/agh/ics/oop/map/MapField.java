package agh.ics.oop.map;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class MapField {
    private final Vector2d position;
    private final List<Animal> animals = new ArrayList<>();

    public MapField(Vector2d position) {
        this.position = position;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    //Tymczasowa funkcja
    public String generuj() {
        String lista = "";
        for(Animal animal : animals) {
            lista += "[A: " + animal.getLocation().toString() + ", " + animal.getDirection().getDirection() + " " + animal.getGenome().getGenome().toString() + "], ";
        }
        return lista;
    }
}
