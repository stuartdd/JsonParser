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
public class JsonObjNum implements JsonObj {
    private final int numValue;

    public JsonObjNum(String stringValue) {
        this.numValue = Integer.parseInt(stringValue);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    
    @Override
    public int size() {
        return numValue;
    }

    @Override
    public String toString() {
        return String.valueOf(numValue);
    }
    
}
