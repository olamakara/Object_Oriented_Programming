package agh.ics.oop.configurations.border;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.utils.Vector2d;

import static agh.ics.oop.elements.Constants.MAP_HEIGHT;
import static agh.ics.oop.elements.Constants.MAP_WIDTH;

public class KulaZiemskaBorder implements IBorderOption {

    @Override
    public Vector2d calculateLocationAfterBorderHit(Vector2d newLocation, Animal animal) {
        int x = newLocation.x();
        int y = newLocation.y();
        if(x < 0) x = MAP_WIDTH-1;
        if(x >= MAP_WIDTH) x = 0;
        if(y < 0) y = MAP_HEIGHT-1;
        if(y >= MAP_HEIGHT) y = 0;
        return new Vector2d(x, y);
    }
}
