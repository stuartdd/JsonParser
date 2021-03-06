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

import parser.exception.JsonParserException;

public class Scanner {

    private final char[] buffer;
    private final int max;

    private int pos;

    public Scanner(final String input) {
        this.buffer = input.toCharArray();
        this.pos = 0;
        this.max = this.buffer.length;
    }

    public char next() {
        if (pos < max) {
            return buffer[pos++];
        }
        return 0;
    }

    public boolean hasNext() {
        return (pos < max);
    }

    public int back() {
        if (pos > 0) {
            pos--;
        }
        return pos;
    }

    public void skipSpace() {
        while (hasNext()) {
            if (next() > ' ') {
                back();
                return;
            }
        }
    }

    public void skipToNext(final char c) {
        while (hasNext()) {
            if (next() == c) {
                back();
                return;
            }
        }
    }

    public boolean isNext(final int mask) {
        if (hasNext()) {
            return CharSet.isAny(buffer[pos], mask);
        }
        return false;
    }

    public Token nextToken() {
        skipSpace();
        if (hasNext()) {
            char c = next();
            if (c == '{') {
                return new Token(null, TokenType.OBJECT_OPEN);
            }
            if (c == '}') {
                return new Token(null, TokenType.OBJECT_CLOSE);
            }
            if (c == ',') {
                return new Token(null, TokenType.COMMA);
            }
            if (c == ':') {
                return new Token(null, TokenType.COLON);
            }
            if (c == '"') {
                return new Token(scanQuotedString(c), TokenType.QUOTED_STRING);
            }
            if (c == '[') {
                return new Token(null, TokenType.ARRAY_OPEN);
            }
            if (c == ']') {
                return new Token(null, TokenType.ARRAY_CLOSE);
            }
            if (CharSet.isAny(c, CharSet.NUM)) {
                back();
                return new Token(scanValueWithMask(CharSet.NUM), TokenType.NUMBER);
            }
            if (CharSet.isAny(c, CharSet.FIRST_NCNAME)) {
                back();
                return new Token(scanValueWithMask(CharSet.NCNAME), TokenType.VALUE);
            }
            throw new JsonParserException("Unrecognised token", this);
        }
        throw new JsonParserException("Unexpected end of string", this);
    }

    public String scanQuotedString(final char delim) {
        StringBuilder sb = new StringBuilder();
        while (hasNext()) {
            char c = next();
            if (c == '\\') {
                c = next();
                if (c == '\\') {
                    sb.append('\\');
                } else {
                    sb.append(c);
                }
            } else {
                if (c == delim) {
                    return sb.toString();
                }
                if (hasNext()) {
                    if (buffer[pos] == delim) {
                        next();
                        sb.append(c);
                        return sb.toString();
                    }
                } else {
                    throw new JsonParserException("Unterminated quoted String", this);
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int from = pos - 40;
        if (from < 0) {
            from = 0;
        }
        int p = from;
        while ((sb.length() < 60) && (p < buffer.length)) {
            if (p == pos) {
                sb.append('|');
            }
            if (buffer[p] < ' ') {
                sb.append(' ');
            } else {
                sb.append(buffer[p]);
            }
            if (p == pos) {
                sb.append('|');
            }
            p++;
        }
        return "Scanner len[" + buffer.length + "] pos[" + pos + "] " + (pos < max ? "next[" + buffer[pos] + "]" : "") + "-->" + sb + "<--";
    }

    private String scanValueWithMask(final int mask) {
        StringBuilder sb = new StringBuilder();
        while (hasNext()) {
            char c = next();
            if (CharSet.isAny(c, mask)) {
                sb.append(c);
            } else {
                back();
                return sb.toString();
            }
        }
        return sb.toString();
    }
}
