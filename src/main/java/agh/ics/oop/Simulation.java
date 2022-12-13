package agh.ics.oop;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.map.GrassGenerator;
import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.Vector2d;

import static agh.ics.oop.elements.Constants.*;

public class Simulation {
    public WorldMap map;
    private GrassGenerator grassGenerator;

    private void simulateDay() {
        //usunięcie martwych zwierząt z mapy,
        //skręt i przemieszczenie każdego zwierzęcia,
        map.moveAnimals();
        //konsumpcja roślin na których pola weszły zwierzęta,
        //rozmnażanie się najedzonych zwierząt znajdujących się na tym samym polu,
        //wzrastanie nowych roślin na wybranych polach mapy.
        grassGenerator.generate(DAILY_GRASS_COUNT);
        //*Minął dzień więc wiek zwierząt się zwiększa
        map.ageAnimals();

        System.out.print(map.generuj());
    }

    public Simulation() {
        map = new WorldMap();
        grassGenerator = new GrassGenerator(map);
        generateAnimals();

        System.out.print(map.generuj());

        for(int i = 0; i < 5; i++) simulateDay();
    }

    public void generateAnimals() {
        for(int i = 0; i < START_ANIMAL_COUNT; i++) {
            generateAnimalRandomly();
        }
    }

    public void generateAnimalRandomly() {
        Animal animal = new Animal(map, Vector2d.random(new Vector2d(MAP_WIDTH, MAP_HEIGHT)));
        map.addAnimal(animal);
    }
}
