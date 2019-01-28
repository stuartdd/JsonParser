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
public class Scanner {

    private final char[] buffer;
    private final int max;

    private int pos;

    public Scanner(String input) {
        this.buffer = input.toCharArray();
        this.pos = 0;
        this.max = this.buffer.length;
    }

    public int pos() {
        return pos;
    }

    public char peek() {
        if (pos < max) {
            return buffer[pos];
        }
        return 0;
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

    public int skipSpace() {
        while (hasNext()) {
            if (next() > ' ') {
                back();
                return pos;
            }
        }
        return 0;
    }

    public int skipToNext(char c) {
        while (hasNext()) {
            if (next() == c) {
                back();
                return pos;
            }
        }
        return 0;
    }

    public boolean isNext(int mask) {
        if (hasNext()) {
            return CharSet.isAny(buffer[pos], mask);
        }
        return false;
    }

    public Token nextToken() {
        if (hasNext()) {
            skipSpace();
            char c = next();
            if (c == '[') {
                return new Token(null, TokenType.ARRAY);
            }
            if (c == ']') {
                return new Token(null, TokenType.ARRAY_CLOSE);
            }
            if (c == '{') {
                return new Token(null, TokenType.OBJECT);
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
            if (CharSet.isAny(c, CharSet.NUM)) {
                back();
                return new Token(scanValue(CharSet.NUM), TokenType.NUMBER);
            }
            if (CharSet.isAny(c, CharSet.FIRST_NCNAME)) {
                back();
                return new Token(scanValue(CharSet.NCNAME), TokenType.VALUE);
            }
            throw new JsonParserException("Unrecognised token", this);
        }
        throw new JsonParserException("Unexpected end of string", this);
    }

    public String scanValue(int mask) {
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

    public String scanQuotedString(char delim) {
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
                if (hasNext()) {
                    if (peek() == delim) {
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
        int from = pos - 20;
        if (from < 0) {
            from = 0;
        }
        int p = from;
        while ((sb.length() < 40) && (p < buffer.length)) {
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
        return "Scanner len[" + buffer.length + "] pos[" + pos + "] " + (pos < max ? "next["+buffer[pos]+"]" : "") + "-->" + sb + "<--";
    }

}
