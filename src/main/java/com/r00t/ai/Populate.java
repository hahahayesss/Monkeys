package com.r00t.ai;

import com.r00t.ai.exceptions.IllegalDNASizeException;
import com.r00t.ai.models.Individual;
import com.r00t.ai.models.Pair;
import com.r00t.ai.models.Parent;

import java.util.ArrayList;
import java.util.List;

class Populate {
    private Integer populationSize;
    private Integer[] target;
    private Class individualClass;
    private List<Pair<Double, Individual>> individuals;

    Populate(Integer populationSize, Integer[] target, Class individualClass) {
        this.populationSize = populationSize;
        this.target = target;
        this.individualClass = individualClass;
        this.individuals = new ArrayList<>();
    }

    void add(Individual individual) {
        individuals.add(new Pair<>(-1.0, individual));
    }

    void bind(Integer DNASize) throws Exception {
        if (individuals.size() != populationSize)
            throw new Exception("Unknown process exception");

        for (int x = 0; x < populationSize; x++) {
            Integer[] tempDNA = new Integer[DNASize];
            for (int y = 0; y < DNASize; y++)
                tempDNA[y] = individuals.get(x).getB().createPartOfDNA();
            individuals.get(x).getB().setDNA(tempDNA);
        }

        calculateFitness();
        calculateProbability();
    }

    void calculateFitness() {
        for (Pair<Double, Individual> individual : individuals) {
            Integer[] dna = individual.getB().getDNA();
            int fitness = 0;
            for (int x = 0; x < dna.length; x++)
                if (target[x].equals(dna[x]))
                    fitness++;
            individual.getB().setFitness(fitness);
        }
    }

    void calculateProbability() {
        Integer sum = 0;
        for (Pair<Double, Individual> temp : individuals)
            sum += temp.getB().getFitness();

        for (int x = 0; x < individuals.size(); x++)
            individuals.get(x)
                    .setA((double) individuals.get(x).getB().getFitness() / sum);
    }

    void nextGeneration(double mutationRatio) throws IllegalAccessException, InstantiationException, IllegalDNASizeException {
        List<Parent> parentList = new ArrayList<>();

        while (individuals.size() > 1) {
            Parent parent = new Parent();

            Pair<Double, Individual> temp = pickOne();
            parent.setOne(temp.getB());
            individuals.remove(temp);

            Pair<Double, Individual> temp2 = pickOne();
            parent.setTwo(temp2.getB());
            individuals.remove(temp2);

            parentList.add(parent);
        }

        for (Parent parent : parentList) {
            parent.createChild(individualClass);
            parent.mutateChild(mutationRatio);
        }

        for (Parent parent : parentList) {
            individuals.add(new Pair<>(-1.0, parent.getOne()));
            individuals.add(new Pair<>(-1.0, parent.getTwo()));
            individuals.add(new Pair<>(-1.0, parent.getChild()));
        }
    }

    private Pair<Double, Individual> pickOne() {
        Integer index = 0;
        Double random = Math.random();
        while (random > 0 && index < individuals.size()) {
            random -= individuals.get(index).getA();
            index++;
        }
        return individuals.get(--index);
    }

    void rearrangePopulationSize() {
        List<Pair<Double, Individual>> newIndividuals = new ArrayList<>();

        for (int x = 0; x < populationSize; x++)
            newIndividuals.add(pickOne());

        individuals = newIndividuals;
        calculateProbability();
    }

    public Integer getPopulationSize() {
        return populationSize;
    }

    public Integer[] getTarget() {
        return target;
    }

    public Class getIndividualClass() {
        return individualClass;
    }

    public List<Pair<Double, Individual>> getIndividuals() {
        return individuals;
    }
}
