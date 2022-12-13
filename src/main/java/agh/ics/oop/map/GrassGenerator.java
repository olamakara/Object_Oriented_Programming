package agh.ics.oop.map;

import agh.ics.oop.utils.Vector2d;

import java.util.*;

import static agh.ics.oop.elements.Constants.*;

public class GrassGenerator {
    private WorldMap map;
    private final Map<Vector2d, MapField> mapFields;
    private final List<Vector2d> emptyFields = new ArrayList<Vector2d>();

    public GrassGenerator(WorldMap map) {
        this.map = map;
        this.mapFields = map.getFields();

        for(int x = 0; x < MAP_WIDTH; x++) {
            for(int y = 0; y < MAP_HEIGHT; y++) {
                emptyFields.add(new Vector2d(x, y));
            }
        }

        generate(START_GRASS_COUNT);
    }

    public void generate(int grassCount) {
        for(int i = 0; i < grassCount; i++) {
            this.generateOne();
        }
    }

    private void generateOne() {
        if(emptyFields.size() == 0) return;

        Random rand = new Random();
        int randIndex = rand.nextInt(emptyFields.size());
        mapFields.get(emptyFields.get(randIndex)).setGrass(true);
        emptyFields.remove(randIndex);
    }
}
