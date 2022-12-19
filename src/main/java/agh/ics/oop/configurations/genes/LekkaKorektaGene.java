package agh.ics.oop.configurations.genes;

import agh.ics.oop.elements.Genome;

import java.util.List;
import java.util.Random;

import static agh.ics.oop.elements.Constants.MAXIMUM_MUTATIONS;
import static agh.ics.oop.elements.Constants.MINIMUM_MUTATIONS;

public class LekkaKorektaGene extends AbstractGeneOption {
    private int newGene(int oldGene) {
        Random rand = new Random();
        if(rand.nextInt(2) == 0) oldGene += 1;
        else oldGene -= 1;
        if(oldGene > 7) oldGene = 0;
        if(oldGene < 0) oldGene = 7;
        return oldGene;
    }

    @Override
    public List<Integer> generateGene(List<Integer> genome) {
        for(int index : this.getRandomIndexes(genome.size(), MINIMUM_MUTATIONS, MAXIMUM_MUTATIONS)) {
            genome.set(index, newGene(genome.get(index)));
        }
        return genome;
    }
}
