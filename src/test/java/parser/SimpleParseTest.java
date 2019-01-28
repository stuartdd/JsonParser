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
import static org.junit.Assert.*;
import org.junit.Test;

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
