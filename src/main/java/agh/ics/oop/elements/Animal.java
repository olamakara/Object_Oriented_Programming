package agh.ics.oop.elements;

import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.Direction;
import agh.ics.oop.utils.Vector2d;

import java.util.Random;

public class Animal {
    private Vector2d location;
    private int energy;
    private Direction direction;
    public Genome genome;
    private WorldMap map;

    public Animal(WorldMap map, Vector2d initialPosition, int startEnergy, int genomeLength) {
        this.location = initialPosition;
        this.energy = startEnergy;
        this.map = map;

        Random rand = new Random();
        this.direction = new Direction(rand.nextInt(8));
        this.genome = new Genome(genomeLength);
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
        Vector2d oldLocation = this.location;
        this.getDirection().rotate(this.getGenome().getCurrentGene());
        Vector2d newLocation = this.direction.calculateMove(this.location);
        //Tymczasowe, potem w zależności od wyboru opcji
        if(newLocation.x() >= 0 && newLocation.x() < this.map.width && newLocation.y() >= 0 && newLocation.y() < this.map.height) {
            this.location = newLocation;
        }
        this.map.moveAnimal(this, oldLocation, this.location);
    }
}