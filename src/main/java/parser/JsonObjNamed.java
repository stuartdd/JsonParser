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
public class JsonObjNamed implements JsonObj {
    private final String name;
    private final JsonObj obj;

    public JsonObjNamed(String name, JsonObj obj) {
        this.name = name;
        this.obj = obj;
    }
    
}
