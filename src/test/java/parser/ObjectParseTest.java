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
public class ObjectParseTest {

    @Test
    public void testParserObjectQSIntAndNull() {
        JsonObj obj = Parser.parse("{\"value\":\"12345A\",\"nullValue\":\"nullStr\"}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(2, obj.size());
        assertEquals("{value=12345A,nullValue=nullStr}", obj.toString());
    }
    
    @Test
    public void testParserObjectIntAndNull() {
        JsonObj obj = Parser.parse("{\"value\":12345,\"nullValue\":null}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(2, obj.size());
        assertEquals("{value=12345,nullValue=null}", obj.toString());
    }
    
    @Test
    public void testParserObjectInt() {
        JsonObj obj = Parser.parse("{\"value\":12345}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(1, obj.size());
        assertEquals("{value=12345}", obj.toString());
    }
    
    @Test
    public void testParserObjectTrue() {
        JsonObj obj = Parser.parse("{\"value\":true}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(1, obj.size());
        assertEquals("{value=true}", obj.toString());
    }
    
    @Test
    public void testParserObjectFalse() {
        JsonObj obj = Parser.parse("{\"value\":false}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(1, obj.size());
        assertEquals("{value=false}", obj.toString());
    }
    
    @Test
    public void testParserObjectNull() {
        JsonObj obj = Parser.parse("{\"value\":null}");
        assertNotNull(obj);
        assertFalse(obj.isEmpty());
        assertEquals("JsonObjMap", obj.getClass().getSimpleName());
        assertEquals(1, obj.size());
        assertEquals("{value=null}", obj.toString());
    }
 
    @Test
    public void testParserObjValueQSInvalidNil() {
        try {
            Parser.parse("{\"true\":nIl}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Expected 'STRING'"));
            return;
        }
        fail("must throw exception");
    }

    @Test
    public void testParserObjValueQSInvalidConst() {
        try {
            Parser.parse("{\"true\":filse}");
        } catch (JsonParserException e) {
            assertTrue(e.getMessage().contains("Expected 'STRING'"));
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
            assertTrue(e.getMessage().contains("Expected 'STRING'"));
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
