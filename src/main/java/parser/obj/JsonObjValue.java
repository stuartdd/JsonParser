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

import java.util.List;
import java.util.Map;

public class JsonObjValue implements JsonObj {

    private final String stringValue;

    public JsonObjValue(final String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public boolean isEmpty() {
        if (stringValue == null) {
            return true;
        }
        return (stringValue.length() == 0);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String toString() {
        return stringValue;
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
        return TYPE.VALUE;
    }

    @Override
    public JsonObj getObj() {
        return null;
    }
}
