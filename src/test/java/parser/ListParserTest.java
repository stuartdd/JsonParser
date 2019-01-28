/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author 802996013
 */
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
