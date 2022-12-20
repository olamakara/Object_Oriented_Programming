package agh.ics.oop.configurations.genes;

import agh.ics.oop.elements.Genome;

import java.util.List;
import java.util.Random;

public class PelnaLosowoscGene extends AbstractGeneOption {

    @Override
    public List<Integer> generateGene(List<Integer> genome, int minimum, int maximum) {
        Random rand = new Random();
        for(int index : this.getRandomIndexes(genome.size(), minimum, maximum)) {
            genome.set(index, rand.nextInt(8));
        }
        return genome;
    }
}
