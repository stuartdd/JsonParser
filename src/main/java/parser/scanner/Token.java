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
package parser.scanner;

public class Token {

    private final String stringValue;
    private final TokenType type;

    public Token(final String stringValue, final TokenType type) {
        this.stringValue = stringValue;
        this.type = type;
    }

    public String getStringValue() {
        return stringValue;
    }

    public TokenType getType() {
        return type;
    }

    public boolean isArrayClose() {
        return type.equals(TokenType.ARRAY_CLOSE);
    }

    public boolean isObjectClose() {
        return type.equals(TokenType.OBJECT_CLOSE);
    }

    public boolean isNotObjectClose() {
        return !type.equals(TokenType.OBJECT_CLOSE);
    }

    public boolean isComma() {
        return type.equals(TokenType.COMMA);
    }

    public boolean isNotComma() {
        return !type.equals(TokenType.COMMA);
    }

    public boolean isColon() {
        return type.equals(TokenType.COLON);
    }

    public boolean isQuotedString() {
        return type.equals(TokenType.QUOTED_STRING);
    }

    boolean isType(TokenType ofType) {
        return this.type.equals(ofType);
    }

    @Override
    public String toString() {
        return "Token :" + type + " :" + stringValue;
    }

}
