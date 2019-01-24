/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.Stack;

/**
 *
 * @author stuart
 */
public class Scanner {

    private char[] buffer;
    private int pos;
    private int max;
    private Stack<Integer> stack;

    public Scanner(String input) {
        this.buffer = input.toCharArray();
        this.pos = 0;
        this.max = this.buffer.length;
        this.stack = new Stack<>();
    }

    public int pos() {
        return pos;
    }

    public char peek() {
        return buffer[pos];
    }

    public char next() {
        return buffer[pos++];
    }

    public boolean hasNext() {
        return (pos < max);
    }

    public int push() {
        stack.push(pos);
        return pos;
    }

    public int pop() {
        if (stack.isEmpty()) {
            pos = 0;
        } else {
            pos = stack.pop();
        }
        return pos;
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
                    throw new JsonParserException("Unterminated quoted String");
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
