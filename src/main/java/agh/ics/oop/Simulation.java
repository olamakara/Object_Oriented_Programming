package agh.ics.oop;

import agh.ics.oop.configurations.border.IBorderOption;
import agh.ics.oop.configurations.border.KulaZiemskaBorder;
import agh.ics.oop.configurations.border.PiekielnyPortalBorder;
import agh.ics.oop.configurations.genes.IGeneOption;
import agh.ics.oop.configurations.genes.LekkaKorektaGene;
import agh.ics.oop.configurations.genes.PelnaLosowoscGene;
import agh.ics.oop.elements.Animal;
import agh.ics.oop.gui.App;
import agh.ics.oop.map.GrassGenerator;
import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.Vector2d;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.elements.Constants.*;
import static agh.ics.oop.options.MapVariant.*;

public class Simulation implements Runnable {
    public WorldMap map;
    private GrassGenerator grassGenerator;
    private int moveDelay;
    public boolean isActive = true;
    private GridPane grid;

    public Simulation(GridPane grid) {
        this.grid = grid;
    }

    private void simulateDay() {
        //usunięcie martwych zwierząt z mapy,
        map.removeDeadAnimals();
        //skręt i przemieszczenie każdego zwierzęcia,
        map.moveAnimals();
        //konsumpcja roślin na których pola weszły zwierzęta,
        map.makeAnimalsEatGrass();
        //rozmnażanie się najedzonych zwierząt znajdujących się na tym samym polu,
        map.makeAnimalsReproduce();
        //wzrastanie nowych roślin na wybranych polach mapy.
        grassGenerator.generate(DAILY_GRASS_COUNT);
        //*Minął dzień więc wiek zwierząt się zwiększa, dodatkowo załatwiamy kwestie UI dla MapFieldów
        map.dayGoneBy();
    }

    public void run() {
        map = new WorldMap(chooseBorderOption(), chooseGeneOption(), grid);
        grassGenerator = new GrassGenerator(map);
        generateAnimals();

        while(isActive) {
            simulateDay();
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException exception) {
                System.out.println("Simulations stopped: " + exception);
            }
        }
    }

    private IBorderOption chooseBorderOption() {
        return switch(MAP_VARIANT) {
            case KULA_ZIEMSKA -> new KulaZiemskaBorder();
            case PIEKIELNY_PORTAL -> new PiekielnyPortalBorder();
        };
    }

    private IGeneOption chooseGeneOption() {
        return switch(GENE_VARIANT) {
            case PELNA_LOSOWOSC -> new PelnaLosowoscGene();
            case LEKKA_KOREKTA -> new LekkaKorektaGene();
        };
    }

    public void generateAnimals() {
        for(int i = 0; i < START_ANIMAL_COUNT; i++) {
            generateAnimalRandomly();
        }
    }

    public void setDelay(int delay) {
        moveDelay = delay;
    }

    public void generateAnimalRandomly() {
        Animal animal = new Animal(map, Vector2d.random(new Vector2d(MAP_WIDTH, MAP_HEIGHT)));
        map.addAnimal(animal);
    }
}
