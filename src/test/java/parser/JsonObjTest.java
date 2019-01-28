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

import static org.junit.Assert.*;
import org.junit.Test;

public class JsonObjTest {

    @Test
    public void testBoolean() {
        assertEquals(":N:U:B:U:U:U:M:U:L:U:S:0:T:E:null", stringObj(new JsonObjValue(null)));
        assertEquals(":N:U:B:U:U:U:M:U:L:U:S:0:T:F:val", stringObj(new JsonObjValue("val")));
        assertEquals(":N:U:B:U:U:U:M:U:L:U:S:0:T:F:null", stringObj(new JsonObjNull()));
        assertEquals(":N:U:B:true:U:U:M:U:L:U:S:0:T:F:true", stringObj(new JsonObjBoolean(true)));
        assertEquals(":N:U:B:false:U:U:M:U:L:U:S:0:T:F:false", stringObj(new JsonObjBoolean(false)));
        assertEquals(":N:U:B:U:U:12345:M:U:L:U:S:0:T:F:12345", stringObj(new JsonObjNum("12345")));
        assertEquals(":N:name:B:U:U:U:M:U:L:U:S:0:T:F:5678", stringObj(new JsonObjNamed("name", new JsonObjNum("5678"))));
        assertEquals(":N:name:B:U:U:U:M:U:L:U:S:0:T:E:null", stringObj(new JsonObjNamed("name", null)));
        JsonObjList list = new JsonObjList();
        assertEquals(":N:U:B:U:U:U:M:U:L:0:S:0:T:E:[]", stringObj(list));
        list.add(new JsonObjNum("12345"));
        assertEquals(":N:U:B:U:U:U:M:U:L:1:S:1:T:F:[12345]", stringObj(list));
        list = new JsonObjList();
        list.add(new JsonObjBoolean(true));
        assertEquals(":N:U:B:U:U:U:M:U:L:1:S:1:T:F:[true]", stringObj(list));
        list.add(new JsonObjBoolean(false));
        assertEquals(":N:U:B:U:U:U:M:U:L:2:S:2:T:F:[true,false]", stringObj(list));
        JsonObjMap map = new JsonObjMap();
        assertEquals(":N:U:B:U:U:U:M:0:L:U:S:0:T:E:{}", stringObj(map));
        map.put("ob1", null);
        assertEquals(":N:U:B:U:U:U:M:1:L:U:S:1:T:F:{ob1=null}", stringObj(map));
        map.put("ob2", new JsonObjNull());
        assertEquals(":N:U:B:U:U:U:M:2:L:U:S:2:T:F:{ob2=null,ob1=null}", stringObj(map));
        map.put("ob3", new JsonObjBoolean(true));
        assertEquals(":N:U:B:U:U:U:M:3:L:U:S:3:T:F:{ob3=true,ob2=null,ob1=null}", stringObj(map));
    }

    private String stringObj(JsonObj o) {
        return (":N:" + (o.getName() == null ? "U" : o.getName())
                + ":B:" + (o.getBoolean() == null ? "U" : o.getBoolean())
                + ":U:" + (o.getNumber() == null ? "U" : o.getNumber().longValue())
                + ":M:" + (o.getMap() == null ? "U" : o.getMap().size())
                + ":L:" + (o.getList() == null ? "U" : o.getList().size())
                + ":S:" + o.size()
                + ":T:" + (o.isEmpty() ? "E" : "F")
                + ":" + o.toString());
    }
}
