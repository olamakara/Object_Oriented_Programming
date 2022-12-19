package agh.ics.oop.configurations.genes;

import agh.ics.oop.elements.Genome;

import java.util.List;
import java.util.Random;

import static agh.ics.oop.elements.Constants.*;

public class PelnaLosowoscGene extends AbstractGeneOption {

    @Override
    public List<Integer> generateGene(List<Integer> genome) {
        Random rand = new Random();
        for(int index : this.getRandomIndexes(genome.size(), MINIMUM_MUTATIONS, MAXIMUM_MUTATIONS)) {
            genome.set(index, rand.nextInt(8));
        }
        return genome;
    }
}
