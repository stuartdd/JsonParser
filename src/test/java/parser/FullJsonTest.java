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

import java.io.IOException;
import parser.common.JsonTestTools;
import parser.obj.JsonObj;
import static org.junit.Assert.*;
import org.junit.Test;

public class FullJsonTest extends JsonTestTools {

    @Test
    public void testFromFile() throws IOException {
        String s = JsonTestTools.getResource("/testParserData.json", this.getClass());
        try {
            JsonObj obj = Parser.parse(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test5() {
        try {
            Parser.parse("{\"P0\":\"\", \"P1\":\"../\"}");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void test4() {
        JsonObj obj = Parser.parse(TEST4);
        System.out.println(obj.toString());
        assertEquals("{me_nu1={header=SVGViewer,items=[null,{id=Open},{id=Original View,label=Original View},null,{id=Quality},{id=Mute},null,{id=Find,label=Find...},{id=Save As,label=Save As},null,{id=Help},{id=About,label=About Adobe CVG Viewer...}]}}", obj.toString());
    }

    @Test
    public void test3() {
        JsonObj obj = Parser.parse(TEST3);
        assertEquals("{widget={image={src=Images/Sun.png,name=sun1},debug=on,window={name=main_window,width=500,height=500},text={size=36.6,align=true}}}", obj.toString());
    }

    @Test
    public void test2() {
        JsonObj obj = Parser.parse(TEST2);
        assertEquals("{menu={popup={menuitem=[{onclick=CreateNewDoc(),value=New},{onclick=OpenDoc(),value=Open},{onclick=CloseDoc(),value=Close}]},id=file}}", obj.toString());
    }

    @Test
    public void test1() {
        JsonObj obj = Parser.parse(TEST1);
        assertEquals("{menu={nullval=null,show=true,id=file,value=123}}", obj.toString());
    }

}
