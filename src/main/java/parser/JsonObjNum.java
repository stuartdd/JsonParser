/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.List;
import java.util.Map;

/**
 *
 * @author stuart
 */
public class JsonObjNum implements JsonObj {

    private final JsonNumber numValue;

    public JsonObjNum(String stringValue) {
        this.numValue = new JsonNumber(stringValue);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String toString() {
        return String.valueOf(numValue);
    }

    @Override
    public Boolean getBoolean() {
        return null;
    }

    @Override
    public Map<String, JsonObj> getMap() {
        return null;
    }

    @Override
    public List<JsonObj> getList() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public JsonNumber getNumber() {
        return numValue;
    }
}
