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
