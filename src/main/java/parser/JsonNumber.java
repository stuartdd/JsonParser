/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author stuart
 */
public class JsonNumber extends Number {

    private final double number;
    private final String numberString;

    public JsonNumber(String numAsString) {
        this.numberString = numAsString;
        try {
            this.number = Double.parseDouble(numAsString);
        } catch (Exception nfe) {
            throw new JsonNumberFormatException(("Json number ["+numberString+"] could not be parsed: "+nfe.getMessage()));
        }
    }

    @Override
    public int intValue() {
        if ((number > Integer.MIN_VALUE) && (number < Integer.MAX_VALUE)) {
            return (int) number;
        }
        throw new JsonNumberRangeException("Value ["+numberString+"] cannot be returned as an int");
    }

    @Override
    public long longValue() {
        if ((number > Long.MIN_VALUE) && (number < Long.MAX_VALUE)) {
            return (long) number;
        }
        throw new JsonNumberRangeException("Value ["+numberString+"] cannot be returned as a long");
    }

    @Override
    public float floatValue() {
        if ((number > Float.MIN_VALUE) && (number < Float.MAX_VALUE)) {
            return (float) number;
        }
        throw new JsonNumberRangeException("Value ["+numberString+"] cannot be returned as a float");
    }

    @Override
    public double doubleValue() {
        return number;
    }

    @Override
    public String toString() {
        return numberString;
    }
    
    
}
