/*
 * Copyright (C) 2019 Stuart Davies (stuartdd)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package parser;

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
