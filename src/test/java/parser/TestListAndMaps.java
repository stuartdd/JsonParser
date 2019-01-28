/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import org.junit.Test;
import parser.common.JsonTestTools;

/**
 *
 * @author stuart
 */
public class TestListAndMaps extends JsonTestTools {

    @Test
    public void test() {
        System.out.println(TEST1);
        Object o1 = Transform.listsAndMaps(TEST1);
        System.out.println(o1);
        System.out.println(TEST2);
        Object o2 = Transform.listsAndMaps(TEST2);
        System.out.println(o2);
        System.out.println(TEST3);
        Object o3 = Transform.listsAndMaps(TEST3);
        System.out.println(o3);
        System.out.println(TEST4);
        Object o4 = Transform.listsAndMaps(TEST4);
        System.out.println(o4);
    }
}
