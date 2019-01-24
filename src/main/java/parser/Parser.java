/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author stuart
 */
public class Parser {

    public static JsonObj parse(String json) {
        return parse(new Scanner(json), true);
    }

    public static JsonObj parse(Scanner scanner, boolean root) {
        while (scanner.hasNext()) {
            scanner.skipSpace();
            if (scanner.hasNext()) {
                if (scanner.next() == '[') {
                    return scanList(scanner);
                } else {
                    scanner.back();
                    if (scanner.next() == '{') {
                        return scanMap();
                    } else {
                        scanner.back();
                        if (root) {
                            throw new JsonParserException("Invalid JSON. Expecting { or { as first non space char");
                        }
                        if (scanner.isNext(CharSet.DQUOTE)) {
                            return scanNamedObj(scanner);
                        } else {
                            scanner.back();
                        }
                    }
                }
            }

        }
        throw new JsonParserException("Invalid JSON. Expecting { or { as first non space char");
    }

    private static JsonObj scanNamedObj(Scanner sc) {
        return new JsonObjNamed(parseForName(sc), parse(sc, false));
    }

    private static String parseForName(Scanner sc) {
        return "";
    }

    private static JsonObj scanMap() {
        return new JsonObjMap();
    }

    private static JsonObj scanList(Scanner sc) {
        JsonObjList list = new JsonObjList();
        list.add(parse(sc, false));
        return list;
    }
}
