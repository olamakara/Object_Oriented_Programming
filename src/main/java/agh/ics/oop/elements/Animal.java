package agh.ics.oop.elements;

import agh.ics.oop.configurations.behaviour.IBehaviourOption;
import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.ConstantsConfig;
import agh.ics.oop.utils.Direction;
import agh.ics.oop.utils.Vector2d;

import java.util.Random;

public class Animal {
    private Vector2d location;
    private int energy;
    private Direction direction;
    public Genome genome;
    private WorldMap map;
    private int currentAge = 0;
    private int childrenCount = 0;
    private final ConstantsConfig currentConfig;

    public Animal(WorldMap map, Vector2d initialPosition, IBehaviourOption behaviourOption, ConstantsConfig currentConfig) {
        this.currentConfig = currentConfig;
        this.location = initialPosition;
        this.energy = currentConfig.getInt("START_ANIMAL_ENERGY");
        this.map = map;

        Random rand = new Random();
        this.direction = new Direction(rand.nextInt(8));
        this.genome = new Genome(currentConfig.getInt("GENOME_LENGTH"), behaviourOption,
                currentConfig.getInt("MINIMUM_MUTATIONS"), currentConfig.getInt("MAXIMUM_MUTATIONS"));
    }

    public Animal(WorldMap map, Vector2d initialPosition, int startEnergy, Genome childGenome, IBehaviourOption behaviourOption, ConstantsConfig currentConfig) {
        this.currentConfig = currentConfig;
        this.location = initialPosition;
        this.energy = startEnergy;
        this.map = map;

        Random rand = new Random();
        this.direction = new Direction(rand.nextInt(8));
        this.genome = childGenome;
    }

    public Vector2d getLocation() {
        return location;
    }

    public Direction getDirection() {
        return direction;
    }

    public Genome getGenome() {
        return genome;
    }

    public void move() {
        this.getDirection().rotate(this.getGenome().getCurrentGene());
        Vector2d newLocation = this.direction.calculateMove(this.location);
        this.location = this.map.moveAnimal(this, this.location, newLocation);
    }

    public void addEnergy(int changeBy) {
        this.energy += changeBy;
    }
    public int getEnergy() {
        return this.energy;
    }
    public int getAge() {
        return this.currentAge;
    }
    public int getChildrenCount() {
        return this.childrenCount;
    }

    public void ageAnimal() {
        this.currentAge += 1;
        this.energy -= this.currentConfig.getInt("DAILY_ENERGY_DECREASE");
    }
}