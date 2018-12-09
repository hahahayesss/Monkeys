package com.r00t.ai;

import com.r00t.ai.exceptions.*;
import com.r00t.ai.listeners.CompleteListener;
import com.r00t.ai.listeners.StateListeners;
import com.r00t.ai.models.Individual;
import com.r00t.ai.models.Statistics;
import com.r00t.ai.validaters.Utils;

public class Population {
    private static volatile Population instance;

    private Integer[] target;
    private Integer populationSize;
    private Double mutationRatio;

    private Populate populate;

    private Statistics statistics;
    private StateStatistics stateStatistics;

    private CompleteListener completeListener;

    public static Population Builder() {
        if (instance == null)
            synchronized (Population.class) {
                if (instance == null)
                    instance = new Population();
            }
        return instance;
    }

    public Population target(Integer[] arr) throws IllegalTargetException {
        Utils.validateTarget(arr);
        target = arr;
        return instance;
    }

    public Population populationSize(Integer size) throws PopulationSizeException {
        Utils.validatePopulationSize(size);
        populationSize = size;
        return instance;
    }

    public Population mutationRatio(Double ratio) throws PopulationRatioException {
        Utils.validatePopulationMutationRatio(ratio);
        mutationRatio = ratio;
        return instance;
    }

    public <T> Population create(Class<T> individual) throws IllegalIndividualObjectException, Exception, IllegalTargetException {
        Utils.validateIndividualClass(individual);
        Utils.validatePopulation(this);

        populate = new Populate(populationSize, target, individual);
        for (int x = 0; x < populationSize; x++)
            populate.add((Individual) individual.newInstance());

        populate.bind(target.length);

        createStatistics();
        return instance;
    }

    private void createStatistics() {
        statistics = new Statistics();
        statistics.setStartTime(String.valueOf(System.currentTimeMillis()));
        statistics.setTargetDNA(target);

        stateStatistics = new StateStatistics(populationSize, target.length);
    }

    public boolean evaluate() throws IllegalAccessException, IllegalDNASizeException, InstantiationException {
        populate.nextGeneration(mutationRatio);
        populate.calculateFitness();
        populate.calculateProbability();
        populate.rearrangePopulationSize();

        stateStatistics.stateChanged(populate.getIndividuals());
        if (stateStatistics.shouldStop()) {
            evaluationComplete(stateStatistics.getValid());
            return true;
        } else
            return false;
    }

    private void evaluationComplete(Individual individual) {
        statistics.setEndTime(String.valueOf(System.currentTimeMillis()));
        statistics.setOutputDNA(individual.getDNA());

        if (completeListener != null)
            completeListener.onComplete(statistics);
    }

    public Integer[] getTarget() {
        return target;
    }

    public Integer getPopulationSize() {
        return populationSize;
    }

    public Double getMutationRatio() {
        return mutationRatio;
    }

    public void setCompleteListener(CompleteListener completeListener) {
        this.completeListener = completeListener;
    }

    public void setStateChangeListener(StateListeners stateChangeListener) {
        stateStatistics.setStateListeners(stateChangeListener);
    }
}
