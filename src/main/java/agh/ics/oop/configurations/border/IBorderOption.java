package agh.ics.oop.configurations.border;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.utils.Vector2d;

public interface IBorderOption {
    Vector2d calculateLocationAfterBorderHit(Vector2d newLocation, Animal animal);
}
