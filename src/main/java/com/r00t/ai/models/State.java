package com.r00t.ai.models;

public class State {
    private Integer generation;
    private double averageFitness;
    private double progress;
    private Individual biggestFitness;

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public void setAverageFitness(double averageFitness) {
        this.averageFitness = averageFitness;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public Individual getBiggestFitness() {
        return biggestFitness;
    }

    public void setBiggestFitness(Individual biggestFitness) {
        this.biggestFitness = biggestFitness;
    }
}
