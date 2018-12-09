package com.r00t.ai;

import com.r00t.ai.exceptions.IllegalIndividualObjectException;
import com.r00t.ai.exceptions.IllegalTargetException;
import com.r00t.ai.exceptions.PopulationRatioException;
import com.r00t.ai.exceptions.PopulationSizeException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PopulationTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void nullTarget() throws IllegalTargetException, IllegalIndividualObjectException, Exception {
        expectedException.expect(IllegalTargetException.class);

        Population.Builder()
                .target(null)
                .populationSize(200)
                .mutationRatio(0.01)
                .create(TestObject.class);
    }

    @Test
    public void emptyTarget() throws IllegalTargetException, IllegalIndividualObjectException, Exception {
        expectedException.expect(IllegalTargetException.class);

        Population.Builder()
                .target(new Integer[]{})
                .populationSize(200)
                .mutationRatio(0.01)
                .create(TestObject.class);
    }

    @Test
    public void nullPopulationSize() throws IllegalTargetException, IllegalIndividualObjectException, Exception {
        expectedException.expect(PopulationSizeException.class);

        Population.Builder()
                .target(new Integer[]{1, 2, 3})
                .populationSize(null)
                .mutationRatio(0.01)
                .create(TestObject.class);
    }

    @Test
    public void zeroPopulationSize() throws IllegalTargetException, IllegalIndividualObjectException, Exception {
        expectedException.expect(PopulationSizeException.class);

        Population.Builder()
                .target(new Integer[]{1, 2, 3})
                .populationSize(0)
                .mutationRatio(0.01)
                .create(TestObject.class);
    }

    @Test
    public void nullMutationRatio() throws IllegalTargetException, IllegalIndividualObjectException, Exception {
        expectedException.expect(PopulationRatioException.class);

        Population.Builder()
                .target(new Integer[]{1, 2, 3})
                .populationSize(1)
                .mutationRatio(null)
                .create(TestObject.class);
    }

    @Test
    public void negativeMutationRatio() throws IllegalTargetException, IllegalIndividualObjectException, Exception {
        expectedException.expect(PopulationRatioException.class);

        Population.Builder()
                .target(new Integer[]{1, 2, 3})
                .populationSize(1)
                .mutationRatio(-1.0)
                .create(TestObject.class);
    }

    @Test
    public void bigMutationRatio() throws IllegalTargetException, IllegalIndividualObjectException, Exception {
        expectedException.expect(PopulationRatioException.class);

        Population.Builder()
                .target(new Integer[]{1, 2, 3})
                .populationSize(1)
                .mutationRatio(2.0)
                .create(TestObject.class);
    }

    @Test
    public void invalidObject() throws IllegalTargetException, IllegalIndividualObjectException, Exception {
        expectedException.expect(IllegalIndividualObjectException.class);

        Population.Builder()
                .target(new Integer[]{1, 2, 3})
                .populationSize(1)
                .mutationRatio(0.5)
                .create(AnotherTestObject.class);
    }

    @Test
    public void notGivenParameters() throws IllegalTargetException, IllegalIndividualObjectException, Exception {
        expectedException.expect(PopulationRatioException.class);

        Population.Builder()
                .target(new Integer[]{1, 2, 3})
                .populationSize(1)
                .create(TestObject.class);
    }
}
