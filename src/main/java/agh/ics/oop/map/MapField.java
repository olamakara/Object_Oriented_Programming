package agh.ics.oop.map;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.utils.Vector2d;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class MapField {
    private final Vector2d position;
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

    public MapField(Vector2d position) {
        this.position = position;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }
    public void setGrass(boolean newState) {
        this.isGrass = newState;
    }

    //Tymczasowa funkcja
    public String generuj() {
        String lista = "";
        if(isGrass) lista = "* G: " + position.toString() + " * ";
        for(Animal animal : animals) {
            lista += "[A: " + animal.getLocation().toString() + ", " + animal.getDirection().getDirection() + " " + animal.getGenome().getGenome().toString() + "], ";
        }
        return lista;
    }
}
