package agh.ics.oop.configurations.border;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.utils.Vector2d;
public class PiekielnyPortalBorder implements IBorderOption {
    @Override
    public Vector2d calculateLocationAfterBorderHit(Vector2d newLocation, Animal animal, int width, int height, int energy) {
        animal.addEnergy(-energy);
        return Vector2d.random(new Vector2d(width, height));
    }
}
