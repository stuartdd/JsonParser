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
package parser.obj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import parser.exception.JsonParserException;

public class JsonObjList implements JsonObj {

    private final List<JsonObj> list = new ArrayList<>();
    private JsonObj nonNullListType = null;

    public void add(JsonObj obj) {
        if (obj instanceof JsonObjNull) {
            list.add(obj);
        } else {
            if (nonNullListType == null) {
                list.add(obj);
                nonNullListType = obj;
            } else {
                if (isCompatibleListType(nonNullListType, obj)) {
                    list.add(obj);
                } else {
                    throw new JsonParserException("Objects in a list must be of the same type", null);
                }
            }
        }
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
        for (JsonObj jo : list) {
            sb.append(jo.toString());
            mark = sb.length();
            sb.append(',');
        }
        sb.setLength(mark);
        return '[' + sb.toString() + ']';
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
        return list;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public JsonNumber getNumber() {
        return null;
    }

    private boolean isCompatibleListType(JsonObj obj1, JsonObj obj2) {
         return (obj1.getClass().getName().equals(obj2.getClass().getName()));
    }
}
