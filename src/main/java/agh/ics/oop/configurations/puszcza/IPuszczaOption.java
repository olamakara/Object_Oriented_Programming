package agh.ics.oop.configurations.puszcza;

import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.Vector2d;

public interface IPuszczaOption {
    boolean checkIfPuszcza(Vector2d location);
    void updatePuszczaFields();
    void updateMap(WorldMap map);
}
