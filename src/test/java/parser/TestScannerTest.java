/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import static org.junit.Assert.*;
import org.junit.Test;
import parser.CharSet;
import parser.Scanner;

/**
 *
 * @author stuart
 */
public class TestScannerTest {

    @Test
    public void quotedStringTestSimple() {
        Scanner si = new Scanner("\"ABC\"");
        si.skipToNext('"');
        assertEquals('"', si.next());
        assertEquals("ABC", si.scanQuotedString('"'));
    }
    @Test
    public void quotedStringTestBraces() {
        Scanner si = new Scanner("\"['] {} , '\"");
        si.skipToNext('"');
        assertEquals('"', si.next());
        assertEquals("['] {} , '", si.scanQuotedString('"'));
    }

    @Test
    public void quotedStringTestEscapes() {
        Scanner si = new Scanner("   \"A'\\\"C \\\\ 1  \"");
        si.skipToNext('"');
        assertEquals('"', si.next());
        assertEquals("A'\"C \\ 1  ", si.scanQuotedString('"'));
    }

    @Test(expected = JsonParserException.class)
    public void quotedStringTestUnterminated() {
        Scanner si = new Scanner(" \"A ");
        si.skipToNext('"');
        assertEquals('"', si.next());
        assertEquals("A ", si.scanQuotedString('"'));
    }

    @Test
    public void testNextChar() {
        Scanner si = new Scanner("   A'\"C  1   ");
        si.skipToNext('\'');
        assertTrue(si.hasNext());
        assertEquals('\'', si.next());
        assertTrue(si.hasNext());
        si.skipToNext('X');
        assertFalse(si.hasNext());
    }

    @Test
    public void testIsNext() {
        Scanner si = new Scanner("   A'\"C  1   ");
        assertTrue(si.isNext(CharSet.WS));
        assertEquals(' ', si.next());
        si.skipSpace();
        assertFalse(si.isNext(CharSet.WS));
        assertTrue(si.isNext(CharSet.ALPHA_NUM));
        assertTrue(si.isNext(CharSet.ALF_UC));
        assertFalse(si.isNext(CharSet.ALF_LC));

        assertEquals('A', si.next());
        assertFalse(si.isNext(CharSet.WS));
        assertFalse(si.isNext(CharSet.ALPHA_NUM));
        assertFalse(si.isNext(CharSet.ALF_UC));
        assertFalse(si.isNext(CharSet.ALF_LC));
        assertTrue(si.isNext(CharSet.SQUOTE));
        assertTrue(si.isNext(CharSet.QUOTE));
        assertFalse(si.isNext(CharSet.DQUOTE));
        assertEquals('\'', si.next());
        assertFalse(si.isNext(CharSet.WS));
        assertFalse(si.isNext(CharSet.ALPHA_NUM));
        assertFalse(si.isNext(CharSet.ALF_UC));
        assertFalse(si.isNext(CharSet.ALF_LC));
        assertTrue(si.isNext(CharSet.DQUOTE));
        assertTrue(si.isNext(CharSet.QUOTE));
        assertFalse(si.isNext(CharSet.SQUOTE));

    }

    @Test
    public void testSkipSpace() {
        Scanner si = new Scanner("   ABC  1   ");
        assertTrue(si.hasNext());
        si.skipSpace();
        assertTrue(si.hasNext());
        assertEquals('A', si.next());
        si.skipSpace();
        assertTrue(si.hasNext());
        assertEquals('B', si.next());
        assertTrue(si.hasNext());
        assertEquals('C', si.next());
        assertTrue(si.hasNext());
        assertEquals(' ', si.next());
        si.skipSpace();
        assertTrue(si.hasNext());
        assertEquals('1', si.next());
        si.skipSpace();
        assertFalse(si.hasNext());
    }

    @Test
    public void testStringIterator() {
        Scanner si = new Scanner("ABC");
        assertTrue(si.hasNext());
        assertEquals('A', si.next());
        assertTrue(si.hasNext());
        assertEquals('B', si.next());
        assertTrue(si.hasNext());
        assertEquals('C', si.next());
        assertFalse(si.hasNext());
    }

    @Test
    public void testStringIteratorPushPop() {
        Scanner si = new Scanner("ABC");
        assertTrue(si.hasNext());
        assertEquals('A', si.next());
        assertTrue(si.hasNext());

        assertEquals(1, si.push());

        assertEquals('B', si.next());
        assertTrue(si.hasNext());
        assertEquals('C', si.next());
        assertFalse(si.hasNext());

        assertEquals(1, si.pop());
        assertEquals('B', si.next());
        assertTrue(si.hasNext());
        assertEquals('C', si.next());
        assertFalse(si.hasNext());

        assertEquals(0, si.pop());

    }

}
