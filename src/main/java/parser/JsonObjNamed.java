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
public class JsonObjNamed implements JsonObj {

    private final String name;
    private final JsonObj obj;

    public JsonObjNamed(String name, JsonObj obj) {
        this.name = name;
        this.obj = obj;
    }

    @Override
    public boolean isEmpty() {
        return (obj == null);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String toString() {
        if (obj == null) {
            return null;
        }
        return obj.toString();
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
        return name;
    }

    @Override
    public JsonNumber getNumber() {
        return null;
    }

}
