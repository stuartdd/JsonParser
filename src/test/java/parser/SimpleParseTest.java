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
    public void testParserEmptyList() {
        JsonObj obj = Parser.parse(" [ ] ");
        assertNotNull(obj);
        assertTrue(obj.isEmpty());
    }

    @Test(expected = JsonParserException.class)
    public void testParserNotJson() {
        Parser.parse(" \"A\"");
    }

    @Test(expected = JsonParserException.class)
    public void testParserNotEmptyJson() {
        Parser.parse(" ");
    }

}
