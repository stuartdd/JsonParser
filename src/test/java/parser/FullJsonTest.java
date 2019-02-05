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
import java.util.Map;
import parser.common.JsonTestTools;
import parser.obj.JsonObj;
import static org.junit.Assert.*;
import org.junit.Test;
import parser.obj.JsonNumber;

public class FullJsonTest extends JsonTestTools {

    @Test
    public void testFromFile() throws IOException {
        String json = JsonTestTools.getResource("/testParserData.json", this.getClass());
        Map<String, Object> map = Transform.flattenOrdered(json);
        assertEquals(true, map.get("functions.echoScriptOutput"));
        assertEquals(200000l, ((Number) map.get("functions.poleForTime")).longValue());
        assertEquals(8080, ((Number) map.get("system.server.port")).intValue());
        assertEquals(20, ((Number) map.get("resources.historyMaxLen")).intValue());
        assertEquals("level", map.get("functions.commands.vol.$1"));
        assertEquals("rsyncLogs", map.get("functions.commands.log.func"));
        assertEquals("", map.get("functions.commands.testtree.P0"));
        assertFalse((Boolean) map.get("validateLocations"));
        assertTrue((Boolean) map.get("allowServerStopCtrl"));
        assertTrue((Boolean) map.get("functions.echoScriptOutput"));
    }

    @Test
    public void testFlattenOrdered1() {
        testFlatOrdered("menu.id=file | menu.nullval=null | menu.show=true | menu.value=123", TEST1);
    }

    @Test
    public void testFlattenOrdered2() {
        testFlatOrdered("menu.id=file | "
                + "menu.popup.menuitem.0.onclick=CreateNewDoc() | "
                + "menu.popup.menuitem.0.value=New | "
                + "menu.popup.menuitem.1.onclick=OpenDoc() | "
                + "menu.popup.menuitem.1.value=Open | "
                + "menu.popup.menuitem.2.onclick=CloseDoc() | "
                + "menu.popup.menuitem.2.value=Close", TEST2);
    }

    @Test
    public void test5() {
        JsonObj obj = Parser.parse("{\"P0\":\"\", \"P1\":\"../\"}");
        assertEquals("{P0=,P1=../}", obj.toString());
    }

    @Test
    public void test4() {
        JsonObj obj = Parser.parse(TEST4);
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
