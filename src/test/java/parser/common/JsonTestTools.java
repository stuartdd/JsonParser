/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import static org.junit.Assert.fail;
import parser.Parser;
import parser.Transform;
import parser.obj.JsonObj;

/**
 *
 * @author stuart
 */
public class JsonTestTools {

    public static final String TEST4 = "{\"me_nu1\":{\"header\":\"SVGViewer\",\"items\":[null,{\"id\":\"Open\"},{\"id\":\"Original View\",\"label\":\"Original View\"},null,{\"id\":\"Quality\"},{\"id\":\"Mute\"},null,{\"id\":\"Find\",\"label\":\"Find...\"},{\"id\":\"Save As\",\"label\":\"Save As\"},null,{\"id\":\"Help\"},{\"id\":\"About\",\"label\":\"About Adobe CVG Viewer...\"}]}}";
    public static final String TEST3 = "{\"widget\":{\"debug\":\"on\",\"window\":{\"name\":\"main_window\",\"width\":500,\"height\":500},\"image\":{\"src\":\"Images/Sun.png\",\"name\":\"sun1\"},\"text\":{\"size\":36.6,\"align\":true}}}";
    public static final String TEST1 = "{\"menu\":{\"id\":\"file\",\"value\":123,\"show\":true,\"nullval\":null}}";
    public static final String TEST2 = "{\"menu\":{\"id\":\"file\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateNewDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Close\",\"onclick\":\"CloseDoc()\"}]}}}";

    public void contains(String[] needles, JsonObj haystack) {
        for (String needle : needles) {
            if (!haystack.toString().contains(needle)) {
                fail("Needle '" + needle + "' not found in haystack '" + haystack.toString() + "'");
            }
        }
    }
    
    public static String getResource(String fileName, Class clazz) throws IOException {
        StringBuilder result = new StringBuilder("");
            InputStream is = clazz.getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException("Failed to find resource file[" + fileName + "]");
            }
            while (is.available() > 0) {
                result.append((char) is.read());
            }
        return result.toString();
    }

    public static String mapString(Map<String, Object> map, String sep) {
        StringBuilder sb = new StringBuilder();
        int mark = 0;
        for (Map.Entry<String, Object> s:map.entrySet()) {
            sb.append(s.getKey()).append('=').append(s.getValue());
            mark = sb.length();
            sb.append(sep);
        }
        sb.setLength(mark);
        return sb.toString();
    }
    
    public void testFlatOrdered(String exp, String json) {
        JsonObj obj = Parser.parse(json);
        String res = mapString(Transform.flattenOrdered(obj), " | ");
        if (exp.equals(res)) {
            return;
        }
        System.out.println("FLATTEN Comparison error:\n"+json+"\n"+exp+"\n"+res);
        fail("FLATTEN Comparison error:\n"+json+"\n"+exp+"\n"+res);
    }
    

}
