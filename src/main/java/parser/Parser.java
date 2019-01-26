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

    private static final String VALUE_LIST = "'STRING', 'NUMBER', 'NULL', 'TRUE', 'FALSE'";

    public static JsonObj parse(String json) {
        Scanner sc = new Scanner(json);
        JsonObj obj = parseListOrObject(sc);
        sc.skipSpace();
        if (sc.hasNext()) {
            throw new JsonParserException("Invalid JSON. Tokens exist after parsing was finished");
        }
        return obj;
    }

    private static JsonObj parseListOrObject(Scanner sc) {
        while (true) {
            Token token = sc.nextToken();
            switch (token.getType()) {
                case ARRAY:
                    return parseList(sc);
                case OBJECT:
                    return parseObject(sc);
                default:
                    throw new JsonParserException("Invalid JSON. Expecting { or [ as first non space char");
            }
        }
    }

    private static JsonObj parseObject(Scanner sc) {
        Token token = sc.nextToken();
        JsonObj result = null;
        JsonObjMap map = new JsonObjMap();
        while (!token.isObjectClose()) {
            if (token.isQuotedString()) {
                String name = token.getStringValue();
                token = sc.nextToken();
                if (token.isColon()) {
                    token = sc.nextToken();
                    String v = token.getStringValue();
                    switch (token.getType()) {
                        case NUMBER:
                            result = new JsonObjNum(v);
                            break;
                        case VALUE:
                            if (v.equalsIgnoreCase("null")) {
                                result = new JsonObjNull();
                            } else {
                                if (v.equalsIgnoreCase("true")) {
                                    result = new JsonObjBoolean(true);
                                } else {
                                    if (v.equalsIgnoreCase("false")) {
                                        result = new JsonObjBoolean(false);
                                    }
                                }
                            }
                            break;
                        case QUOTED_STRING:
                            result = new JsonObjNamed(name, new JsonObjValue(v));
                            break;
                    }
                    if (result == null) {
                        throw new JsonParserException("Expected " + VALUE_LIST + ", '{', '[' after ':'");
                    }
                    token = sc.nextToken();
                    map.put(name, result);
                    if (token.isObjectClose()) {
                        return map;
                    }
                    if (!token.isComma()) {
                        throw new JsonParserException("Expected a valid " + VALUE_LIST);
                    }
                } else {
                    throw new JsonParserException("Expected a ':' after a named object");
                }
            } else {
                throw new JsonParserException("Expected a quoted string value for a named object");
            }
            token = sc.nextToken();
        }
        throw new JsonParserException("Expected an object");
    }

    private static JsonObj parseList(Scanner sc) {
        JsonObjList list = new JsonObjList();
        Token token = sc.nextToken();
        TokenType listType = token.getType();
        while (!token.isArrayClose()) {
            if (token.isType(listType)) {
                switch (token.getType()) {
                    case NUMBER:
                        list.add(new JsonObjNum(token.getStringValue()));
                        break;
                    case VALUE:
                    case QUOTED_STRING:
                        list.add(new JsonObjValue(token.getStringValue()));
                        break;
                    case ARRAY:
                        list.add(parseList(sc));
                }
            } else {
                throw new JsonParserException("Array element must be of the same type");
            }
            token = sc.nextToken();
            if (token.isComma() || (token.isArrayClose())) {
                if (token.isComma()) {
                    token = sc.nextToken();
                }
            } else {
                throw new JsonParserException("Unexpected end of list");
            }
        }
        return list;
    }
}
