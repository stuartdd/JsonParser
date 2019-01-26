/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author stuart
 */
public class JsonObjMap implements JsonObj {

    private final Map<String, JsonObj> map = new HashMap<>();

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }

    void put(String name, JsonObj result) {
        map.put(name, result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int mark = 0;
        for (Map.Entry<String, JsonObj> e : map.entrySet()) {
             sb.append(e.getKey()).append('=').append((e.getValue()==null?"null":e.getValue().toString()));
            mark = sb.length();
            sb.append(',');
        }
        sb.setLength(mark);
        return '{' + sb.toString() + '}';
    }

    @Override
    public Boolean getBoolean() {
        return null;
    }

    @Override
    public Map<String, JsonObj> getMap() {
        return map;
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
