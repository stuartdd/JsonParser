/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stuart
 */
public class JsonObjList implements JsonObj {
    List<JsonObj> list = new ArrayList<>();
    
    public void add(JsonObj obj) {
        list.add(obj);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int mark = 0;
        for (JsonObj jo:list) {
            sb.append(jo.toString());
            mark = sb.length();
            sb.append(',');
        }
        sb.setLength(mark);
        return '['+sb.toString()+']';
    }

    
    
}
