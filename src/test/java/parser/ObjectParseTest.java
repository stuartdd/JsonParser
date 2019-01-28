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

import parser.common.JsonTestTools;
import parser.exception.JsonParserException;
import parser.obj.JsonObj;
import static org.junit.Assert.*;
import org.junit.Test;

public class ObjectParseTest extends JsonTestTools {

    @Test
    public void testParserObjectQSIntFloat() {
        JsonObj obj = Parser.parse("{\"int\":12345,\"double\":1.23456789,\"minus\":-12.4,\"plus\":+123}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(4, obj.size());
        contains(new String[] {"minus=-12.4","double=1.23456789","int=12345","plus=+123"}, obj);
    }

    @Test
    public void testParserObjectQSIntAndNull() {
        JsonObj obj = Parser.parse("{\"value\":\"12345A\",\"nullValue\":\"nullStr\"}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(2, obj.size());
        contains(new String[] {"value=12345A","nullValue=nullStr"}, obj);
    }

    @Test
    public void testParserObjectIntAndNull() {
        JsonObj obj = Parser.parse("{\"value\":12345,\"nullValue\":null}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(2, obj.size());
        contains(new String[] {"value=12345","nullValue=null"}, obj);
    }

    @Test
    public void testParserObjectInt() {
        JsonObj obj = Parser.parse("{\"value\":12345}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(1, obj.size());
        contains(new String[] {"value=12345"}, obj);
    }

    @Test
    public void testParserObjectTrue() {
        JsonObj obj = Parser.parse("{\"value\":true}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(1, obj.size());
        contains(new String[] {"value=true"}, obj);
    }

    @Test
    public void testParserObjectFalse() {
        JsonObj obj = Parser.parse("{\"value\":false}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(1, obj.size());
        contains(new String[] {"value=false"}, obj);
    }

    @Test
    public void testParserObjectNull() {
        JsonObj obj = Parser.parse("{\"value\":null}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(1, obj.size());
        contains(new String[] {"value=null"}, obj);
    }

    @Test
    public void testParserObjValueQSInvalidName() {
        try {
            Parser.parse("{\"tr ue\":null}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Name value"));
            return;
        }
        fail("must throw exception");
    }
    
    @Test
    public void testParserObjValueQSInvalidNil() {
        try {
            Parser.parse("{\"true\":nIl}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Expected either null"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserObjValueQSInvalidConst() {
        try {
            Parser.parse("{\"true\":filse}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Expected either null"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserObjValueQSInvalidNumObj() {
        try {
            Parser.parse("{\"true\":12s}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Expected a valid"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserObjValueQSInvalidObj() {
        try {
            Parser.parse("{\"true\":hi}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Expected either null"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserObjValueQSNoObject() {
        try {
            Parser.parse("{\"true\":}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Expected 'STRING'"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserObjValueQSNoColon() {
        try {
            Parser.parse("{\"true\"}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Expected a ':'"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserObjValueNotQS() {
        try {
            Parser.parse("{true}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("quoted string"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserObjEmpty() {
        try {
            Parser.parse("{}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Expected"));
            return;
        }
        fail("must throw exception");
    }

}
