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
