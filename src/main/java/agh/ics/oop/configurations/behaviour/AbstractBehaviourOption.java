package agh.ics.oop.configurations.behaviour;

public abstract class AbstractBehaviourOption implements IBehaviourOption {
    protected int getNextGeneNormally(int length, int currentGene) {
        return (currentGene+1)%length;
    }
}
