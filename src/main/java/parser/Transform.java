/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import parser.obj.JsonObj;

/**
 *
 * @author stuart
 */
public class Transform {

    public static Object listsAndMaps(String json) {
        return listsAndMaps(Parser.parse(json));
    }

    public static Object listsAndMaps(JsonObj obj) {
        switch (obj.type()) {
            case NUM:
                return obj.getNumber();
            case NULL:
                return null;
            case VALUE:
                return obj.toString();
            case NAMED:
                return obj.toString();
            case BOOL:
                return obj.getBoolean();
            case LIST:
                List<Object> list = new ArrayList();
                for (JsonObj jo : obj.getList()) {
                    list.add(listsAndMaps(jo));
                }
                return list;
            case MAP:
                Map<String, Object> map = new HashMap<>();
                for (Map.Entry<String, JsonObj> ent : obj.getMap().entrySet()) {
                    map.put(ent.getKey(), listsAndMaps(ent.getValue()));
                }
                return map;
        }
        return null;
    }

}
