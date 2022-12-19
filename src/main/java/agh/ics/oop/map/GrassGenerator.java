package agh.ics.oop.map;

import agh.ics.oop.configurations.puszcza.IPuszczaOption;
import agh.ics.oop.utils.Vector2d;

import java.util.*;

import static agh.ics.oop.elements.Constants.*;

public class GrassGenerator implements IGrassGenObserver {
    private WorldMap map;
    private final Map<Vector2d, MapField> mapFields;
    private final List<Vector2d> emptyFields = new ArrayList<Vector2d>();
    private final IPuszczaOption puszczaOption;

    public GrassGenerator(WorldMap map, IPuszczaOption puszczaOption) {
        this.map = map;
        this.puszczaOption = puszczaOption;
        this.mapFields = map.getFields();
        for(MapField field : this.mapFields.values()) {
            field.addGrassGenObserver(this);
        }

        for(int x = 0; x < MAP_WIDTH; x++) {
            for(int y = 0; y < MAP_HEIGHT; y++) {
                emptyFields.add(new Vector2d(x, y));
            }
        }

        generate(START_GRASS_COUNT);
    }

    public void freeGrass(Vector2d position) {
        emptyFields.add(position);
    }

    public void generate(int grassCount) {
        this.puszczaOption.updatePuszczaFields();

        List<Vector2d> puszczaFields = new ArrayList<Vector2d>();
        List<Vector2d> normalFields = new ArrayList<Vector2d>();

        for(Vector2d field : this.emptyFields) {
            if(this.puszczaOption.checkIfPuszcza(field)) puszczaFields.add(field);
            else normalFields.add(field);
        }

        for(int i = 0; i < grassCount; i++) {
            this.generateOne(puszczaFields, normalFields);
        }
    }

    private void generateOne(List<Vector2d> puszczaFields, List<Vector2d> normalFields) {
        if(emptyFields.size() == 0) return;

        Random rand = new Random();

        if(rand.nextInt(5) == 0) {
            if(normalFields.size() > 0) getAndRemoveRandomFromList(normalFields);
            else if(puszczaFields.size() > 0) getAndRemoveRandomFromList(puszczaFields);
        } else {
            if(puszczaFields.size() > 0) getAndRemoveRandomFromList(puszczaFields);
            else if(normalFields.size() > 0) getAndRemoveRandomFromList(normalFields);
        }
    }

    private void getAndRemoveRandomFromList(List<Vector2d> list) {
        Random rand = new Random();

        int randIndex = rand.nextInt(list.size());
        Vector2d element = list.get(randIndex);

        mapFields.get(element).setGrass(true);
        emptyFields.remove(element);
        list.remove(element);
    }
}
