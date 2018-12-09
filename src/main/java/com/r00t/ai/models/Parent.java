package com.r00t.ai.models;

import com.r00t.ai.exceptions.IllegalDNASizeException;

public class Parent {
    private Individual one;
    private Individual two;
    private Individual child;

    public void createChild(Class individual) throws IllegalDNASizeException, IllegalAccessException, InstantiationException {
        if (one.getDNA().length != two.getDNA().length)
            throw new IllegalDNASizeException("DNA size has to be same");

        child = (Individual) individual.newInstance();

        Integer[] childDNA = new Integer[one.getDNA().length];
        for (int x = 0; x < one.getDNA().length; x++) {
            Integer partOfDNA;

            if (Math.random() < 0.5)
                partOfDNA = one.getDNA()[x];
            else
                partOfDNA = two.getDNA()[x];

            childDNA[x] = partOfDNA;
        }

        child.setDNA(childDNA);
    }

    public void mutateChild(double mutationRatio) {
        Integer[] DNA = child.getDNA();

        for (int x = 0; x < DNA.length; x++)
            if (Math.random() < mutationRatio)
                DNA[x] = child.createPartOfDNA();

        child.setDNA(DNA);
    }

    public Individual getOne() {
        return one;
    }

    public void setOne(Individual one) {
        this.one = one;
    }

    public Individual getTwo() {
        return two;
    }

    public void setTwo(Individual two) {
        this.two = two;
    }

    public Individual getChild() {
        return child;
    }
}
