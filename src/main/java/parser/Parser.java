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

public class Parser {

    private static final String VALUE_LIST = "'STRING', 'NUMBER', 'NULL', 'TRUE', 'FALSE'";

    public static JsonObj parse(String json) {
        Scanner sc = new Scanner(json);
        JsonObj obj = null;
        try {
            obj = parseListOrObject(sc);
        } catch (JsonParserException pe) {
            pe.setScanner(sc);
            throw pe;
        }
        sc.skipSpace();
        if (sc.hasNext()) {
            throw new JsonParserException("Invalid JSON. Tokens exist after parsing was finished", sc);
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
                    throw new JsonParserException("Invalid JSON. Expecting { or [ as first non space char", sc);
            }
        }
    }

    private static JsonObj parseObject(Scanner sc) {
        Token token = sc.nextToken();
        JsonObj result = null;
        JsonObjMap map = new JsonObjMap();
        while (!token.isObjectClose()) {
            if (token.isQuotedString()) {
                String name = validateName(token.getStringValue(), sc);
                token = sc.nextToken();
                if (token.isColon()) {
                    token = sc.nextToken();
                    String v = token.getStringValue();
                    switch (token.getType()) {
                        case NUMBER:
                            result = new JsonObjNum(v);
                            break;
                        case VALUE:
                            result = objectForTokenValue(token.getStringValue(), sc);
                            break;
                        case QUOTED_STRING:
                            result = new JsonObjNamed(name, new JsonObjValue(v));
                            break;
                        case ARRAY:
                            result = parseList(sc);
                            break;
                        case OBJECT:
                            result = parseObject(sc);
                            break;
                    }
                    if (result == null) {
                        throw new JsonParserException("Expected " + VALUE_LIST + ", '{', '[' after ':'", sc);
                    }
                    token = sc.nextToken();
                    map.put(name, result);
                    if (token.isObjectClose()) {
                        return map;
                    }
                    if (!token.isComma()) {
                        throw new JsonParserException("Expected a valid " + VALUE_LIST, sc);
                    }
                } else {
                    throw new JsonParserException("Expected a ':' after a named object", sc);
                }
            } else {
                throw new JsonParserException("Expected a quoted string value for a named object", sc);
            }
            token = sc.nextToken();
        }
        throw new JsonParserException("Expected an object", sc);
    }

    private static JsonObj parseList(Scanner sc) {
        Token token = sc.nextToken();
        JsonObjList list = new JsonObjList();
        JsonObj result = null;
        while (!token.isArrayClose()) {
            switch (token.getType()) {
                case NUMBER:
                    result = new JsonObjNum(token.getStringValue());
                    break;
                case VALUE:
                    result = objectForTokenValue(token.getStringValue(), sc);
                    break;
                case QUOTED_STRING:
                    result = new JsonObjValue(token.getStringValue());
                    break;
                case ARRAY:
                    result = parseList(sc);
                    break;
                case OBJECT:
                    result = parseObject(sc);
            }
            list.add(result);

            token = sc.nextToken();
            if (token.isComma() || (token.isArrayClose())) {
                if (token.isComma()) {
                    token = sc.nextToken();
                }
            } else {
                throw new JsonParserException("Unexpected end of list", sc);
            }
        }
        return list;
    }

    private static String validateName(String name, Scanner sc) {
        for (char c:name.toCharArray()) {
            if (!CharSet.isAny(c, CharSet.NCNAME)) {
                throw new JsonParserException("Name value ["+name+"] is invalid", sc);
            }
        }
        return name;
    }
    private static JsonObj objectForTokenValue(String tokenValue, Scanner sc) {
        if (tokenValue.equalsIgnoreCase("null")) {
            return new JsonObjNull();
        } else {
            if (tokenValue.equalsIgnoreCase("true")) {
                return new JsonObjBoolean(true);
            } else {
                if (tokenValue.equalsIgnoreCase("false")) {
                    return new JsonObjBoolean(false);
                }
            }
        }
        throw new JsonParserException("Expected either null, true, false, a number or a quoted String. Received '" + tokenValue + "'.", sc);
    }
}
