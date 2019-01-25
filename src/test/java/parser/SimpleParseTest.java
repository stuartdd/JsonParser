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
public class SimpleParseTest {

    @Test
    public void testParserNameNoObject() {
        JsonObj obj = Parser.parse(" [ \"STRING\" ] ");
        assertNotNull(obj);
    }

    @Test
    public void testParserSB() {
        JsonObj obj = Parser.parse(" [] ");
        assertNotNull(obj);
    }

    @Test
    public void testParserCB() {
        JsonObj obj = Parser.parse(" {} ");
        assertNotNull(obj);
    }

    @Test(expected = JsonParserException.class)
    public void testParserNotJson() {
        JsonObj obj = Parser.parse(" \"A\"");
        assertNotNull(obj);
    }

    @Test(expected = JsonParserException.class)
    public void testParserNotEmptyJson() {
        JsonObj obj = Parser.parse(" ");
        assertNotNull(obj);
    }

}
