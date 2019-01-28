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

import parser.exception.JsonParserException;
import parser.obj.JsonObj;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ListParserTest {

    @Test
    public void testParserRecursiveList2() {
        JsonObj obj = Parser.parse("[[false, true, true],[1,2]]");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjList", obj.getClass().getSimpleName());
        assertEquals(2, obj.size());
        assertEquals("[[false,true,true],[1,2]]", obj.toString());
    }

    @Test
    public void testParserRecursiveList1() {
        JsonObj obj = Parser.parse("[[true, false, true]]");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjList", obj.getClass().getSimpleName());
        assertEquals(1, obj.size());
        assertEquals("[[true,false,true]]", obj.toString());
    }

    @Test
    public void testParserRecursiveListUnbalenced1() {
        try {
            Parser.parse(" [ [true ]");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Unexpected"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserRecursiveListUnbalenced2() {
        try {
            Parser.parse(" [true,] ]");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Tokens exist after"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserRecursiveListDiffTypes1() {
        try {
            Parser.parse(" [ 12324, [ false, true] ] ");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("same type"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserRecursiveListDiffTypes2() {
        try {
            Parser.parse(" [ [ false, true], 123 ] ");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("same type"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserListSimpleValue3() {
        JsonObj obj = Parser.parse(" [ true, true, false] ");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjList", obj.getClass().getSimpleName());
        assertEquals(3, obj.size());
        assertEquals("[true,true,false]", obj.toString());
    }

    @Test
    public void testParserListSimpleNums3() {
        JsonObj obj = Parser.parse(" [123,456,789] ");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjList", obj.getClass().getSimpleName());
        assertEquals(3, obj.size());
        assertEquals("[123,456,789]", obj.toString());
    }

    @Test
    public void testParserListSimpleQuoted3() {
        JsonObj obj = Parser.parse(" [\"123\",\"true\",\"ABC\"] ");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjList", obj.getClass().getSimpleName());
        assertEquals(3, obj.size());
        assertEquals("[123,true,ABC]", obj.toString());
    }

    @Test
    public void testParserListSimpleValue() {
        JsonObj obj = Parser.parse(" [ 12345 ] ");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals(1, obj.size());
        assertEquals("JsonObjList", obj.getClass().getSimpleName());
        assertEquals("[12345]", obj.toString());
    }

    @Test
    public void testParserListDiffTypes() {
        try {
            Parser.parse(" [ \"value\", 123 ] ");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("same type"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserListNoComma() {
        try {
            Parser.parse(" [ \"value\" 123 ] ");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Unexpected"));
            return;
        }
        fail("must throw exception");
    }

}
