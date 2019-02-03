/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.Assert.fail;
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


}
