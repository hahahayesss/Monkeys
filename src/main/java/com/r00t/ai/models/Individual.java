package com.r00t.ai.models;

public abstract class Individual {

    public abstract void setDNA(Integer[] DNA);

    public abstract Integer[] getDNA();

    public abstract Integer createPartOfDNA();

    public abstract void setFitness(Integer fitness);

    public abstract Integer getFitness();
}
