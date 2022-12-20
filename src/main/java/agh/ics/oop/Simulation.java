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
import agh.ics.oop.options.BehaviourVariant;
import agh.ics.oop.options.GeneVariant;
import agh.ics.oop.options.MapVariant;
import agh.ics.oop.options.PuszczaVariant;
import agh.ics.oop.utils.ConstantsConfig;
import agh.ics.oop.utils.Vector2d;
import javafx.scene.layout.GridPane;

import static agh.ics.oop.options.BehaviourVariant.*;
import static agh.ics.oop.options.GeneVariant.*;
import static agh.ics.oop.options.PuszczaVariant.*;

public class Simulation implements Runnable {
    public WorldMap map;
    private GrassGenerator grassGenerator;
    private int moveDelay;
    public boolean isActive = true;
    private GridPane grid;
    public ConstantsConfig currentConfig;
    private int width;
    private int height;

    public Simulation(GridPane grid, ConstantsConfig currentConfig) {
        this.currentConfig = currentConfig;
        this.width = currentConfig.getInt("MAP_WIDTH");
        this.height = currentConfig.getInt("MAP_HEIGHT");
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
        grassGenerator.generate(currentConfig.getInt("DAILY_GRASS_COUNT"));
        //*Minął dzień więc wiek zwierząt się zwiększa, dodatkowo załatwiamy kwestie UI dla MapFieldów
        map.dayGoneBy();
    }

    public void run() {
        IPuszczaOption puszczaOption = choosePuszczaOption();
        map = new WorldMap(chooseBorderOption(), chooseGeneOption(), chooseBehaviourOption(), puszczaOption, grid, currentConfig);
        grassGenerator = new GrassGenerator(map, puszczaOption, currentConfig);
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
        return switch((MapVariant)currentConfig.get("MAP_VARIANT")) {
            case KULA_ZIEMSKA -> new KulaZiemskaBorder();
            case PIEKIELNY_PORTAL -> new PiekielnyPortalBorder();
        };
    }

    private IGeneOption chooseGeneOption() {
        return switch((GeneVariant)currentConfig.get("GENE_VARIANT")) {
            case PELNA_LOSOWOSC -> new PelnaLosowoscGene();
            case LEKKA_KOREKTA -> new LekkaKorektaGene();
        };
    }

    private IBehaviourOption chooseBehaviourOption() {
        return switch((BehaviourVariant)currentConfig.get("GENE_JUMP_VARIANT")) {
            case PELNA_PROKRASTYNACJA -> new PelnaProkrastynacjaBehaviour();
            case NIECO_SZALENSTWA -> new NiecoSzalenstwaBehaviour();
        };
    }

    private IPuszczaOption choosePuszczaOption() {
        return switch((PuszczaVariant)currentConfig.get("PUSZCZA_VARIANT")) {
            case ZALESIONE_ROWNIKI -> new ZalesioneRownikiPuszcza(width, height);
            case TOKSYCZNE_TRUPY -> new ToksyczeTrupyPuszcza(width, height);
        };
    }

    public void generateAnimals() {
        for(int i = 0; i < currentConfig.getInt("START_ANIMAL_COUNT"); i++) {
            generateAnimalRandomly();
        }
    }

    public void setDelay(int delay) {
        moveDelay = delay;
    }

    public void generateAnimalRandomly() {
        Animal animal = new Animal(map, Vector2d.random(new Vector2d(width, height)), chooseBehaviourOption(), currentConfig);
        map.addAnimal(animal);
    }
}
