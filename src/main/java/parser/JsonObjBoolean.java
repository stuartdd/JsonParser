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
public class JsonObjBoolean implements JsonObj {

    private final boolean bool;

    public JsonObjBoolean(boolean bool) {
        this.bool = bool;
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
        return String.valueOf(bool);
    }

    @Override
    public Boolean getBoolean() {
        return bool;
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
        return null;
    }
}
