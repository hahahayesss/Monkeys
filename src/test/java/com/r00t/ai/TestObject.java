package com.r00t.ai;

import com.r00t.ai.models.Individual;

import java.util.Random;

public class TestObject extends Individual {
    private String alphabet = "qwertyuıopğüasdfghjklşi,zxcvbnmöç. ";
    private Integer[] DNA;
    private Integer fitness;

    @Override
    public void setDNA(Integer[] DNA) {
        this.DNA = DNA;
    }

    @Override
    public Integer[] getDNA() {
        return this.DNA;
    }

    @Override
    public Integer createPartOfDNA() {
        return new Random().nextInt(alphabet.length());
    }

    @Override
    public void setFitness(Integer fitness) {
        this.fitness = fitness;
    }

    @Override
    public Integer getFitness() {
        return this.fitness;
    }
}