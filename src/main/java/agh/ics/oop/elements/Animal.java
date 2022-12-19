package agh.ics.oop.elements;

import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.Direction;
import agh.ics.oop.utils.Vector2d;

import java.util.Random;

import static agh.ics.oop.elements.Constants.*;

public class Animal {
    private Vector2d location;
    private int energy;
    private Direction direction;
    public Genome genome;
    private WorldMap map;
    private int currentAge = 0;
    private int childrenCount = 0;

    public Animal(WorldMap map, Vector2d initialPosition) {
        this.location = initialPosition;
        this.energy = START_ANIMAL_ENERGY;
        this.map = map;

        Random rand = new Random();
        this.direction = new Direction(rand.nextInt(8));
        this.genome = new Genome(GENOME_LENGTH);
    }

    public Animal(WorldMap map, Vector2d initialPosition, int startEnergy, Genome childGenome) {
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
        this.energy -= DAILY_ENERGY_DECREASE;
    }
}