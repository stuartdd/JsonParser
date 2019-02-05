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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void put(final String name, final JsonObj result) {
        map.put(name, result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int mark = 0;
        for (Map.Entry<String, JsonObj> e : map.entrySet()) {
            sb.append(e.getKey()).append('=').append((e.getValue() == null ? "null" : e.getValue().toString()));
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

    @Override
    public TYPE type() {
        return TYPE.MAP;
    }
    
    @Override
    public JsonObj getObj() {
        return null;
    }
}
