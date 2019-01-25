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
public class JsonObjTrue implements JsonObj {
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
        return "true";
    }
    
}
