package agh.ics.oop;

import agh.ics.oop.configurations.border.IBorderOption;
import agh.ics.oop.configurations.border.KulaZiemskaBorder;
import agh.ics.oop.configurations.border.PiekielnyPortalBorder;
import agh.ics.oop.configurations.behaviour.IBehaviourOption;
import agh.ics.oop.configurations.behaviour.NiecoSzalenstwaBehaviour;
import agh.ics.oop.configurations.behaviour.PelnaProkrastynacjaBehaviour;
import agh.ics.oop.configurations.genes.IGeneOption;
import agh.ics.oop.configurations.genes.LekkaKorektaGene;
import agh.ics.oop.configurations.genes.PelnaLosowoscGene;
import agh.ics.oop.configurations.puszcza.IPuszczaOption;
import agh.ics.oop.configurations.puszcza.ToksyczeTrupyPuszcza;
import agh.ics.oop.configurations.puszcza.ZalesioneRownikiPuszcza;
import agh.ics.oop.elements.Animal;
import agh.ics.oop.map.GrassGenerator;
import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.Vector2d;
import javafx.scene.layout.GridPane;

import static agh.ics.oop.elements.Constants.*;

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
        IPuszczaOption puszczaOption = choosePuszczaOption();
        map = new WorldMap(chooseBorderOption(), chooseGeneOption(), chooseBehaviourOption(), puszczaOption, grid);
        grassGenerator = new GrassGenerator(map, puszczaOption);
        puszczaOption.updateMap(map);
        generateAnimals();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while(isActive) {
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException exception) {
                System.out.println("Simulations stopped: " + exception);
            }
            simulateDay();
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

    private IBehaviourOption chooseBehaviourOption() {
        return switch(GENE_JUMP_VARIANT) {
            case PELNA_PROKRASTYNACJA -> new PelnaProkrastynacjaBehaviour();
            case NIECO_SZALENSTWA -> new NiecoSzalenstwaBehaviour();
        };
    }

    private IPuszczaOption choosePuszczaOption() {
        return switch(PUSZCZA_VARIANT) {
            case ZALESIONE_ROWNIKI -> new ZalesioneRownikiPuszcza(MAP_WIDTH, MAP_HEIGHT);
            case TOKSYCZNE_TRUPY -> new ToksyczeTrupyPuszcza(MAP_WIDTH, MAP_HEIGHT);
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
        Animal animal = new Animal(map, Vector2d.random(new Vector2d(MAP_WIDTH, MAP_HEIGHT)), chooseBehaviourOption());
        map.addAnimal(animal);
    }
}
