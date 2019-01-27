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

    private static final String TEST1 = "{\"menu\":{\"id\":\"file\",\"value\":123,\"show\":true,\"nullval\":null}}";
    private static final String TEST2 = "{\"menu\":{\"id\":\"file\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]}}}";

    @Test
    public void test2() {
        System.out.println(TEST2);
        JsonObj obj = Parser.parse(TEST2);
        System.out.println(obj);
    }

    @Test
    public void test1() {
        JsonObj obj = Parser.parse(TEST1);
        System.out.println(obj);
        assertEquals("{menu={nullval=null,show=true,id=file,value=123}}", obj.toString());
    }

}
