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
public interface JsonObj {
    boolean isEmpty();
    int size();
    JsonNumber getNumber();
    String getName();
    Boolean getBoolean();
    Map<String, JsonObj> getMap();
    List<JsonObj> getList();
}
