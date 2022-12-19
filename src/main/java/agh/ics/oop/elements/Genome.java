package agh.ics.oop.elements;

import agh.ics.oop.configurations.behaviour.IBehaviourOption;
import agh.ics.oop.configurations.genes.IGeneOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genome {
    private List<Integer> genome = new ArrayList<Integer>();
    private IBehaviourOption behaviourOption;
    private int currentGene;
    private int length;

    //W przypadku nie podania żadnych innych genotypów, na których podstawie genom miałby się tworzyc
    //to jest on losowany (losowe liczby od 0 do 7 włącznie, N takich liczb)
    public Genome(int length, IBehaviourOption behaviourOption) {
        Random rand = new Random();

        for(int i = 0; i < length; i++) {
            this.genome.add(rand.nextInt(8));
        }

        this.behaviourOption = behaviourOption;
        this.currentGene = rand.nextInt(length);
        this.length = length;
    }

    public Genome(int length, IGeneOption geneOption, Genome gen1, Genome gen2, int parent1Energy, int parent2Energy, IBehaviourOption behaviourOption) {
        Random rand = new Random();

        List<Integer> newGenome = new ArrayList<>();
        newGenome.addAll((parent1Energy < parent2Energy) ? gen1.getGenome() : gen2.getGenome());

        int genesFromStronger = (parent1Energy < parent2Energy) ?
                (int)(length*((double)parent1Energy/parent2Energy)) : (int)(length*((double)parent2Energy/parent1Energy));

        if(rand.nextInt(2) == 0) {
            for(int i = 0; i < genesFromStronger; i++) {
                newGenome.set(i, (parent1Energy < parent2Energy) ? gen2.getGenome().get(i) : gen1.getGenome().get(i));
            }
        } else {
            for(int i = length-1; i > Math.max(length-genesFromStronger-1, -1); i--) {
                newGenome.set(i, (parent1Energy < parent2Energy) ? gen2.getGenome().get(i) : gen1.getGenome().get(i));
            }
        }

        this.behaviourOption = behaviourOption;
        this.genome = geneOption.generateGene(newGenome);
        this.currentGene = rand.nextInt(length);
        this.length = length;
    }

    public int getCurrentGene() {
        int toReturn = genome.get(currentGene);
        currentGene = behaviourOption.getNextGene(length, currentGene);

        return toReturn;
    }

    public List<Integer> getGenome() {
        return this.genome;
    }
}
