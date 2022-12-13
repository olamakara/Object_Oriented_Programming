package agh.ics.oop;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.gui.App;
import agh.ics.oop.gui.IAppObserver;
import agh.ics.oop.map.GrassGenerator;
import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.elements.Constants.*;

public class Simulation implements Runnable {
    public WorldMap map;
    private GrassGenerator grassGenerator;
    private int moveDelay;
    private List<IAppObserver> observers = new ArrayList<>();


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

    public void run() {
        map = new WorldMap();
        grassGenerator = new GrassGenerator(map);
        generateAnimals();

        System.out.print(map.generuj());

        for(int i = 0; i < 5; i++) {
            simulateDay();
            for (IAppObserver observer: observers) {
                observer.positionChanged(i);
            }
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException exception) {
                System.out.println("Simulations stopped: " + exception);
            }
        }
    }

    public void generateAnimals() {
        for(int i = 0; i < START_ANIMAL_COUNT; i++) {
            generateAnimalRandomly();
        }
    }

    public void setDelay(int delay) {
        moveDelay = delay;
    }
    public void addObserver(IAppObserver observer) {
        observers.add(observer);
    }


    public void generateAnimalRandomly() {
        Animal animal = new Animal(map, Vector2d.random(new Vector2d(MAP_WIDTH, MAP_HEIGHT)));
        map.addAnimal(animal);
    }
}
