package com.r00t.ai.validaters;

import com.r00t.ai.Population;
import com.r00t.ai.exceptions.IllegalIndividualObjectException;
import com.r00t.ai.exceptions.IllegalTargetException;
import com.r00t.ai.exceptions.PopulationRatioException;
import com.r00t.ai.exceptions.PopulationSizeException;

public class Utils {
    public static void validateTarget(Integer[] target) throws IllegalTargetException {
        if (target == null)
            throw new IllegalTargetException("Target can't be null");
        else if (target.length < 1)
            throw new IllegalTargetException("Target length can't be zero (0)");
    }

    public static void validatePopulationSize(Integer size) throws PopulationSizeException {
        if (size == null)
            throw new PopulationSizeException("Population size can't be null");
        else if (size < 0)
            throw new PopulationSizeException("Population size can't smaller than 0");
        else if (size == 0)
            throw new PopulationSizeException("Population size can't be 0");
    }

    public static void validatePopulationMutationRatio(Double ratio) throws PopulationRatioException {
        if (ratio == null)
            throw new PopulationRatioException("Population mutation ratio can't be null");
        else if (ratio < 0)
            throw new PopulationRatioException("Population mutation ratio can't smaller than 0");
        else if (ratio > 1)
            throw new PopulationRatioException("Population mutation ratio can't bigger than 1");
    }

    public static <T> void validateIndividualClass(Class<T> cls) throws IllegalIndividualObjectException {
        if (!cls.getSuperclass().getName().equals("com.r00t.ai.models.Individual"))
            throw new IllegalIndividualObjectException("Individuals has to be extend from com.r00t.ai.models.Individual.class");
    }

    public static void validatePopulation(Population population) throws IllegalTargetException {
        validateTarget(population.getTarget());
        validatePopulationSize(population.getPopulationSize());
        validatePopulationMutationRatio(population.getMutationRatio());
    }
}
