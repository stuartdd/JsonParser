/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author 802996013
 */
public class Token {
    private final String stringValue;
    private final TokenType type;

    public Token(String stringValue, TokenType type) {
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

    public boolean isComma() {
        return type.equals(TokenType.COMMA);
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
        return "Token :" + type + " :"+stringValue;
    }
    
}
