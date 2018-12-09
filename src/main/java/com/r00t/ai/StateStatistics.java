package com.r00t.ai;

import com.r00t.ai.listeners.StateListeners;
import com.r00t.ai.models.Individual;
import com.r00t.ai.models.Pair;
import com.r00t.ai.models.State;

import java.util.ArrayList;
import java.util.List;

public class StateStatistics {
    private static Integer generation = 0;

    private final Integer populationSize;
    private final Integer targetLength;

    private List<State> stateList;

    private Long fullFitness;
    private StateListeners stateListeners;
    private Individual founded;

    StateStatistics(Integer populationSize, Integer targetLength) {
        this.populationSize = populationSize;
        this.targetLength = targetLength;
        bind();
    }

    private void bind() {
        stateList = new ArrayList<>();
        fullFitness = (long) (populationSize * targetLength);
    }

    void stateChanged(List<Pair<Double, Individual>> individuals) {
        long sumOfFitness = calculateSumOfFitnesses(individuals);

        State state = new State();
        state.setGeneration(++generation);
        state.setAverageFitness(
                (double) sumOfFitness / individuals.size()
        );
        state.setProgress(
                (double) sumOfFitness / fullFitness
        );
        state.setBiggestFitness(
                findBiggestFitness(individuals)
        );

        for (Pair<Double, Individual> individual : individuals) {
            if (individual.getB().getFitness().equals(targetLength))
                founded = individual.getB();
        }

        stateList.add(state);

        if (stateListeners != null)
            stateListeners.onStateChange(state);
    }

    private long calculateSumOfFitnesses(List<Pair<Double, Individual>> list) {
        return list.stream()
                .map(d -> d.getB())
                .map(i -> i.getFitness())
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Individual findBiggestFitness(List<Pair<Double, Individual>> individuals) {
        Individual individual = individuals.get(0).getB();
        for (Pair<Double, Individual> doubleIndividualPair : individuals)
            if (doubleIndividualPair.getB().getFitness() > individual.getFitness())
                individual = doubleIndividualPair.getB();
        return individual;
    }

    boolean shouldStop() {
        return founded != null;
    }

    Individual getValid() {
        return founded;
    }

    void setStateListeners(StateListeners stateListeners) {
        this.stateListeners = stateListeners;
    }

    public List<State> getStateList() {
        return stateList;
    }
}
