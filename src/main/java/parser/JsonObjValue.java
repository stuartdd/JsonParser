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
public class JsonObjValue implements JsonObj {

    private final String stringValue;

    public JsonObjValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public boolean isEmpty() {
        return (stringValue.length() == 0);
    }

    @Override
    public int size() {
        if (stringValue == null) {
            return 0;
        }
        return stringValue.length();
    }

    @Override
    public String toString() {
        return stringValue;
    }

}