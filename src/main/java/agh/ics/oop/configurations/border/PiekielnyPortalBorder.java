package agh.ics.oop.configurations.border;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.utils.Vector2d;

import static agh.ics.oop.elements.Constants.*;

public class PiekielnyPortalBorder implements IBorderOption {
    @Override
    public Vector2d calculateLocationAfterBorderHit(Vector2d newLocation, Animal animal) {
        animal.addEnergy(-BREEDING_ENERGY);
        return Vector2d.random(new Vector2d(MAP_WIDTH, MAP_HEIGHT));
    }
}
