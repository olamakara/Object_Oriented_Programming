package agh.ics.oop.configurations.behaviour;

import java.util.Random;

public class NiecoSzalenstwaBehaviour extends AbstractBehaviourOption {

    @Override
    public int getNextGene(int length, int currentGene) {
        Random rand = new Random();
        if(rand.nextInt(5) == 0) {
            return getNextGeneRandomly(length);
        }
        return this.getNextGeneNormally(length, currentGene);
    }

    public int getNextGeneRandomly(int length) {
        Random rand = new Random();
        return rand.nextInt(length);
    }
}
