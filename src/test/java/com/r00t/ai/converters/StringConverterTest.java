package com.r00t.ai.converters;

import com.r00t.ai.exceptions.IllegalStringConverterParameterException;
import org.junit.Assert;
import org.junit.Test;

public class StringConverterTest {
    @Test
    public void MIXConvertTest() throws IllegalStringConverterParameterException {
        StringConverter stringConverter = new StringConverter();
        Integer[] output = stringConverter.convertString("ahmet", StringConverter.Mode.MIX);

        Assert.assertArrayEquals(output, new Integer[]{26, 2, 3, 4, 5});
    }
}
