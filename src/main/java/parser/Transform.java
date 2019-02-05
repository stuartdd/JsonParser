/*
 * Copyright (C) 2019 Stuart Davies (stuartdd)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import parser.obj.JsonObj;

/**
 *
 * @author stuart
 */
public class Transform {

    public static Map<String, Object> flattenOrdered(final String json) {
        return flattenOrdered(Parser.parse(json));
    }

    public static Map<String, Object> flattenUnOrdered(final String json) {
        return flattenUnOrdered(Parser.parse(json));
    }

    public static Map<String, Object> flattenOrdered(final JsonObj obj) {
        return flatten(obj, new TreeMap<>());
    }

    public static Map<String, Object> flattenUnOrdered(final JsonObj obj) {
        return flatten(obj, new HashMap<>());
    }

    public static Map<String, Object> flatten(final JsonObj obj, final Map<String, Object> map) {
        flatten(obj, map, new Stack<>());
        return map;
    }

    public static Object listsAndMaps(final String json) {
        return listsAndMaps(Parser.parse(json));
    }

    public static Object listsAndMaps(final JsonObj obj) {
        switch (obj.type()) {
            case NUM:
                return (Number) obj.getNumber();
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

    private static void flatten(final JsonObj obj, final Map<String, Object> map, final Stack<String> stack) {
        switch (obj.type()) {
            case NUM:
                map.put(generateKey(stack), obj.getNumber());
                break;
            case NULL:
                map.put(generateKey(stack), null);
                break;
            case VALUE:
                map.put(generateKey(stack), obj.toString());
                break;
            case NAMED:
                map.put(generateKey(stack), obj.toString());
                break;
            case BOOL:
                map.put(generateKey(stack), obj.getBoolean());
                break;
            case LIST:
                for (int i = 0; i < obj.getList().size(); i++) {
                    stack.push("" + i);
                    flatten(obj.getList().get(i), map, stack);
                    stack.pop();
                }
                break;
            case MAP:
                for (Map.Entry<String, JsonObj> ent : obj.getMap().entrySet()) {
                    stack.push(ent.getKey());
                    flatten(ent.getValue(), map, stack);
                    stack.pop();
                }
        }
    }

    private static String generateKey(final Stack<String> stack) {
        StringBuilder sb = new StringBuilder();
        int mark = 0;
        for (int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));
            mark = sb.length();
            sb.append('.');
        }
        sb.setLength(mark);
        return sb.toString();
    }

}
