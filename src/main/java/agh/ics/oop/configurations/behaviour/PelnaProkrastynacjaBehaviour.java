package agh.ics.oop.configurations.behaviour;

public class PelnaProkrastynacjaBehaviour extends AbstractBehaviourOption {

    @Override
    public int getNextGene(int length, int currentGene) {
        return this.getNextGeneNormally(length, currentGene);
    }
}
