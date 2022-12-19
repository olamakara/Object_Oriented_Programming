package agh.ics.oop.configurations.genes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractGeneOption implements IGeneOption {
    protected List<Integer> getRandomIndexes(int length, int minimum, int maximum) {
        List<Integer> possibleIndexes = new ArrayList<>();
        List<Integer> randomedIndexes = new ArrayList<>();
        if(maximum == 0) return randomedIndexes;

        for(int i = 0; i < length; i++) possibleIndexes.add(i);

        Random rand = new Random();
        int randomCount = rand.nextInt(maximum-minimum)+minimum;
        for(int i = 0; i < randomCount; i++) {
            int randomed = rand.nextInt(possibleIndexes.size());
            randomedIndexes.add(possibleIndexes.get(randomed));
            possibleIndexes.remove(randomed);
        }

        return randomedIndexes;
    }
}
