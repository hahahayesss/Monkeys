package com.r00t.ai;

import org.junit.Assert;
import org.junit.Test;

public class PopulateTest {
    @Test
    public void constructorTest() {
        Populate populate = new Populate(
                1,
                new Integer[]{1, 2},
                TestObject.class
        );

        Assert.assertEquals(Long.valueOf(populate.getPopulationSize()), Long.valueOf(1));
        Assert.assertEquals(Long.valueOf(populate.getTarget().length), Long.valueOf(2));
        Assert.assertEquals(populate.getIndividualClass(), TestObject.class);
    }
}
