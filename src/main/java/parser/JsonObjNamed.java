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
        if (isEmpty()) {
            return 0;
        }
        return obj.size();
    }

    
}
