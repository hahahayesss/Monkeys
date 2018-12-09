package com.r00t.ai.converters;

import com.r00t.ai.exceptions.IllegalStringConverterParameterException;

public class StringConverter {
    private String mix = "\"1234567890*-qwertyuıopğüasdfghjklşi,zxcvbnmöç.<!'^+%&/()=?_>£#$½{[]}\\@~;:|* ";
    private String alphabet = "qwertyuıopğüasdfghjklşizxcvbnmöç ";
    private String numbers = "0123456789 ";

    public Integer[] convertString(String data, Mode mode) throws IllegalStringConverterParameterException {
        if (data == null)
            throw new IllegalStringConverterParameterException("The String can't be null");
        else if (data.isEmpty())
            throw new IllegalStringConverterParameterException("The String can't be empty");

        data = data.toLowerCase();

        switch (mode) {
            case MIX:
                return convert(data, mix);
            case ALPHABET_ONLY:
                return convert(data, alphabet);
            case NUMBERS_ONLY:
                return convert(data, numbers);
            default:
                throw new IllegalStringConverterParameterException("Illegal converter mode. Converter mode has to be MIX or ALPHABET or NUMBER.");
        }
    }

    private Integer[] convert(String data, String dataSet) {
        Integer[] output = new Integer[data.length()];

        for (int x = 0; x < data.length(); x++)
            for (int y = 0; y < dataSet.length(); y++)
                if (data.charAt(x) == dataSet.charAt(y))
                    output[x] = y;

        return output;
    }

    public enum Mode {
        MIX,
        ALPHABET_ONLY,
        NUMBERS_ONLY;
    }
}
