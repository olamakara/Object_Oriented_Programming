package agh.ics.oop.configurations.border;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.utils.Vector2d;

public class KulaZiemskaBorder implements IBorderOption {

    @Override
    public Vector2d calculateLocationAfterBorderHit(Vector2d newLocation, Animal animal, int width, int height, int energy) {
        int x = newLocation.x();
        int y = newLocation.y();
        if(x < 0) x = width-1;
        if(x >= width) x = 0;
        if(y < 0) y = height-1;
        if(y >= height) y = 0;
        return new Vector2d(x, y);
    }
}
