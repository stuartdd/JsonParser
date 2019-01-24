/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author stuart
 */
public class JsonObjMap implements JsonObj {
    Map<String, JsonObj> map = new HashMap<>();
}
