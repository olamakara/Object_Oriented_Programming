package agh.ics.oop.map;

import agh.ics.oop.configurations.behaviour.IBehaviourOption;
import agh.ics.oop.configurations.genes.IGeneOption;
import agh.ics.oop.configurations.puszcza.IPuszczaOption;
import agh.ics.oop.elements.Animal;
import agh.ics.oop.elements.Genome;
import agh.ics.oop.utils.ConstantsConfig;
import agh.ics.oop.utils.Vector2d;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;
public class MapField {
    private final Vector2d position;
    private final GridPane grid;

    private Rectangle grassRect;
    private Rectangle animalRect;
    private IGrassGenObserver grassGenObserver;
    private WorldMap map;
    private IGeneOption geneOption;
    private IBehaviourOption behaviourOption;
    private IPuszczaOption puszczaOption;
    private ConstantsConfig currentConfig;
    private int deathCount = 0;

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

    public MapField(Vector2d position, GridPane grid, WorldMap map, IGeneOption geneOption, IBehaviourOption behaviourOption, IPuszczaOption puszczaOption, ConstantsConfig currentConfig) {
        this.grid = grid;
        this.currentConfig = currentConfig;
        this.position = position;
        this.map = map;
        this.geneOption = geneOption;
        this.behaviourOption = behaviourOption;
        this.puszczaOption = puszczaOption;
        Platform.runLater(this::generateUIElements);
    }

    private void generateUIElements() {
        int boxSize = currentConfig.getInt("UI_BOX_SIZE");
        int animalSize = currentConfig.getInt("UI_ANIMAL_SIZE");

        grassRect = new Rectangle(boxSize, boxSize);
        grassRect.setFill(Color.color(0.13, 0.61, 0));
        grassRect.setStroke(Color.color(0.13, 0.61, 0));
        grassRect.setOpacity(this.isGrass ? 1 : 0);
        grid.add(grassRect, position.x(), position.y());
        GridPane.setHalignment(grassRect, HPos.CENTER);

        animalRect = new Rectangle(animalSize, animalSize);
        animalRect.setFill(Color.color(0.8, 0, 0));
        animalRect.setStroke(Color.color(0.8, 0, 0));
        animalRect.setVisible(false);
        grid.add(animalRect, position.x(), position.y());
        GridPane.setHalignment(animalRect, HPos.CENTER);

        Platform.runLater(this::manageUI);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal, boolean isDead) {
        animals.remove(animal);
        if(isDead) deathCount += 1;
    }

    public void addGrassGenObserver(IGrassGenObserver observer) {
        this.grassGenObserver = observer;
    }

    public void eatGrass() {
        if(this.isGrass && animals.size() != 0) {
            animals.first().addEnergy(currentConfig.getInt("GRASS_EATEN_ENERGY"));
            this.isGrass = false;
            this.grassGenObserver.freeGrass(this.position);
        }
    }

    public void reproduce() {
        int breedingEnergy = currentConfig.getInt("BREEDING_ENERGY");
        int genomeLength = currentConfig.getInt("GENOME_LENGTH");
        if(animals.size() >= 2) {
            Animal parent1 = animals.first();
            Animal parent2 = (Animal) animals.toArray()[1];
            if(parent2.getEnergy() >= breedingEnergy) {
                parent1.addEnergy(-breedingEnergy);
                parent2.addEnergy(-breedingEnergy);
                Genome childGenome = new Genome(genomeLength, geneOption, parent1.getGenome(), parent2.getGenome(), parent1.getEnergy(), parent2.getEnergy(), behaviourOption);
                Animal child = new Animal(map, position, breedingEnergy*2, childGenome, behaviourOption, currentConfig);
                this.addAnimal(child);
                this.map.addAnimal(child);
            }
        }
    }

    public void manageUI() {
        if(animalRect == null || grassRect == null) return;
        grassRect.setOpacity(this.isGrass ? 1 : (puszczaOption.checkIfPuszcza(position) ? 0.3 : 0));

        Color grassColor = (puszczaOption.checkIfPuszcza(position)) ? Color.color(0.04, 0.29, 0) : Color.color(0.13, 0.61, 0);
        grassRect.setFill(grassColor);
        grassRect.setStroke(grassColor);

        if(animals.size() == 0) {
            animalRect.setOpacity(0);
            animalRect.setVisible(false);
        }
        else {
            animalRect.setOpacity(Math.min(1, (double)animals.first().getEnergy() / currentConfig.getInt("FULL_ANIMAL_ENERGY")));
            animalRect.setVisible(true);
        }
    }

    public int getDeathCount() {
        return this.deathCount;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public void setGrass(boolean newState) {
        this.isGrass = newState;
    }
}