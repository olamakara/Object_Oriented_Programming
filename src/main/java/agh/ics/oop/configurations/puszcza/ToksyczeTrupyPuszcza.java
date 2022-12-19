package agh.ics.oop.configurations.puszcza;

import agh.ics.oop.elements.Animal;
import agh.ics.oop.map.MapField;
import agh.ics.oop.map.WorldMap;
import agh.ics.oop.utils.Vector2d;

import java.util.*;

public class ToksyczeTrupyPuszcza implements IPuszczaOption {
    private final List<Vector2d> puszczaFields = new ArrayList<Vector2d>();
    private List<MapField> preparedRandomFields;
    private WorldMap map;
    private final int width;
    private final int height;

    public ToksyczeTrupyPuszcza(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private void updatePuszczaFieldsLocal() {
        if(this.map == null) return;
        this.puszczaFields.removeAll(this.puszczaFields);

        Random rand = new Random();

        List<MapField> fields = new ArrayList<>(map.getFields().values().stream().toList());
        fields.sort(new Comparator<MapField>() {
            @Override
            public int compare(MapField o1, MapField o2) {
                if(o1.getDeathCount() == o2.getDeathCount()) {
                    return preparedRandomFields.indexOf(o1)-preparedRandomFields.indexOf(o2);
                }
                return o1.getDeathCount()-o2.getDeathCount();
            }
        });

        for(int i = 0; i < (int)((double)(width*height)/5); i++) {
            this.puszczaFields.add(fields.get(i).getPosition());
        }
    }

    @Override
    public boolean checkIfPuszcza(Vector2d location) {
        return puszczaFields.contains(location);
    }

    @Override
    public void updatePuszczaFields() {
        this.updatePuszczaFieldsLocal();
    }

    @Override
    public void updateMap(WorldMap map) {
        this.map = map;
        this.preparedRandomFields = new ArrayList<>(map.getFields().values().stream().toList());
        Collections.shuffle(this.preparedRandomFields);
        updatePuszczaFieldsLocal();
    }
}
