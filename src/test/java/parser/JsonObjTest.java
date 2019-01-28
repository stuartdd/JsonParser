/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author stuart
 */
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
