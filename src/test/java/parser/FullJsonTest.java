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

import parser.obj.JsonObj;
import static org.junit.Assert.*;
import org.junit.Test;

public class FullJsonTest {

    private static final String TEST4 = "{\"me_nu1\":{\"header\":\"SVGViewer\",\"items\":[null,{\"id\":\"Open\"},{\"id\":\"Original View\",\"label\":\"Original View\"},null,{\"id\":\"Quality\"},{\"id\":\"Mute\"},null,{\"id\":\"Find\",\"label\":\"Find...\"},{\"id\":\"Save As\",\"label\":\"Save As\"},null,{\"id\":\"Help\"},{\"id\":\"About\",\"label\":\"About Adobe CVG Viewer...\"}]}}";
    private static final String TEST3 = "{\"widget\":{\"debug\":\"on\",\"window\":{\"name\":\"main_window\",\"width\":500,\"height\":500},\"image\":{\"src\":\"Images/Sun.png\",\"name\":\"sun1\"},\"text\":{\"size\":36.6,\"align\":true}}}";
    private static final String TEST1 = "{\"menu\":{\"id\":\"file\",\"value\":123,\"show\":true,\"nullval\":null}}";
    private static final String TEST2 = "{\"menu\":{\"id\":\"file\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]}}}";

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
