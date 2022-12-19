package agh.ics.oop.configurations.puszcza;

import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class ZalesioneRownikiPuszcza implements IPuszczaOption {
    List<Vector2d> puszczaFields = new ArrayList<Vector2d>();

    public ZalesioneRownikiPuszcza(int width, int height) {
        int potentialHalfRemoval = (height%2 == 0) ? (height-2)/2 : (height-1)/2; //PP - Połowa problemu
        int additionalGrassOnHalf = (int)((double)potentialHalfRemoval/5);
        int notPuszczaFromEdge = potentialHalfRemoval - additionalGrassOnHalf;

        //ranges inclusive
        int startHeight = notPuszczaFromEdge-1;
        int endHeight = height-notPuszczaFromEdge;

        for(int i = startHeight; i <= endHeight; i++) {
            for(int j = 0; j < width; j++) {
                puszczaFields.add(new Vector2d(j, i));
            }
        }
    }

    @Override
    public boolean checkIfPuszcza(Vector2d location) {
        return puszczaFields.contains(location);
    }

    @Override
    public void updatePuszczaFields() { }

    @Override
    public void updateMap(WorldMap map) { }
}
