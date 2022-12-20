package agh.ics.oop.utils;

import java.util.HashMap;
import java.util.Map;

public class ConstantsConfig {
    public Map<String, Object> values;
    public ConstantsConfig(Map<String, Object> values) {
        this.values = values;
    }

    public int getInt(String fieldName) {
        return (int)values.get(fieldName);
    }

    public boolean getBool(String fieldName) { return (boolean) values.get(fieldName); }

    public Object get(String fieldName) { return values.get(fieldName); }
}
