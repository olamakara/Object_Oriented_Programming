package agh.ics.oop.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genome {
    private List<Integer> genome = new ArrayList<Integer>();
    private int currentGene;
    private int length;
    //W przypadku nie podania żadnych innych genotypów, na których podstawie genom miałby się tworzyc
    //to jest on losowany (losowe liczby od 0 do 7 włącznie, N takich liczb)
    public Genome(int length) {
        Random rand = new Random();

        for(int i = 0; i < length; i++) {
            this.genome.add(rand.nextInt(8));
        }

        this.currentGene = rand.nextInt(length);
        this.length = length;
    }

    public int getCurrentGene() {
        int toReturn = genome.get(currentGene);
        currentGene = (currentGene + 1) % length;

        return toReturn;
    }

    public List<Integer> getGenome() {
        return this.genome;
    }
}
