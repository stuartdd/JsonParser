/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package parser;

/**
 *
 * @author 802996013
 */
public class CharSet {
    private static final int NULL =     0x00000000;
    public static final int ALF_LC =   0x00000002;
    public static final int ALF_UC =   0x00000004;
    private static final int ALF =      0x00000008;

    public static final int SPACE =    0x00000010;
    public static final int WS =       0x00000020;
    public static final int US =       0x00000040;
    public static final int NUM =      0x00000001;
    public static final int DQUOTE =   0x00000080;
    public static final int SQUOTE =   0x00000100;
    public static final int DOT =      0x00000200;
    public static final int HYPHEN =   0x00000400;
    public static final int ESCAPE =   0x00000800;
    
    
    
    public static final int ALPHA_NUM = ALF | NUM;
    public static final int ALPHA = ALF;
    public static final int FIRST_NCNAME = ALF | US;
    public static final int NCNAME = ALF | NUM | US | DOT | HYPHEN;
    public static final int QUOTE = DQUOTE | SQUOTE;
    
private static final int[] masks = {
    WS,WS,WS,WS,WS,WS,WS,WS,
    WS,WS,WS,WS,WS,WS,WS,WS,
    WS,WS,WS,WS,WS,WS,WS,WS,
    WS,WS,WS,WS,WS,WS,WS,WS,
    SPACE | WS,         // SP   (Space)
    NULL,               // !    (exclamation mark)
    DQUOTE,             // "    (double quote)
    NULL,               // #    (number sign)
    NULL,               // $    (dollar sign)
    NULL,               // %    (percent)
    NULL,               // &    (ampersand)
    SQUOTE,             // '    (single quote)
    NULL,               // (  (left/open parenthesis)
    NULL,               // )  (right/closing parenth.)
    NULL,               // *    (asterisk)
    NULL | NUM,         // +    (plus)
    NULL,               // ,    (comma)
    HYPHEN | NUM,       // -    (minus or dash)
    DOT | NUM,          // .    (dot)
    NULL,               // /    (forward slash)
    NUM,                // 0
    NUM,                // 1
    NUM,                // 2
    NUM,                // 3
    NUM,                // 4
    NUM,                // 5
    NUM,                // 6
    NUM,                // 7
    NUM,                // 8
    NUM,                // 9
    NULL,               // :    (colon)
    NULL,               // ;    (semi-colon)
    NULL,               // <    (less than)
    NULL,               // =    (equal sign)
    NULL,               // >    (greater than)
    NULL,               // ?    (question mark)
    NULL,               // @    (AT symbol)
    ALF_UC | ALF,       // A
    ALF_UC | ALF,       // B
    ALF_UC | ALF,       // C
    ALF_UC | ALF,       // D
    ALF_UC | ALF,       // E
    ALF_UC | ALF,       // F
    ALF_UC | ALF,       // G
    ALF_UC | ALF,       // H
    ALF_UC | ALF,       // I
    ALF_UC | ALF,       // J
    ALF_UC | ALF,       // K
    ALF_UC | ALF,       // L
    ALF_UC | ALF,       // M
    ALF_UC | ALF,       // N
    ALF_UC | ALF,       // O
    ALF_UC | ALF,       // P
    ALF_UC | ALF,       // Q
    ALF_UC | ALF,       // R
    ALF_UC | ALF,       // S
    ALF_UC | ALF,       // T
    ALF_UC | ALF,       // U
    ALF_UC | ALF,       // V
    ALF_UC | ALF,       // W
    ALF_UC | ALF,       // X
    ALF_UC | ALF,       // Y
    ALF_UC | ALF,       // Z
    NULL,               // [    (left/opening bracket)
    ESCAPE,             // \    (back slash)
    NULL,               // ]    (right/closing bracket)
    NULL,               // ^    (caret/circumflex)
    US,                 // _    (underscore)
    SQUOTE,             // `
    ALF_LC | ALF,       // a
    ALF_LC | ALF,       // b
    ALF_LC | ALF,       // c
    ALF_LC | ALF,       // d
    ALF_LC | ALF,       // e
    ALF_LC | ALF,       // f
    ALF_LC | ALF,       // g
    ALF_LC | ALF,       // h
    ALF_LC | ALF,       // i
    ALF_LC | ALF,       // j
    ALF_LC | ALF,       // k
    ALF_LC | ALF,       // l
    ALF_LC | ALF,       // m
    ALF_LC | ALF,       // n
    ALF_LC | ALF,       // o
    ALF_LC | ALF,       // p
    ALF_LC | ALF,       // q
    ALF_LC | ALF,       // r
    ALF_LC | ALF,       // s
    ALF_LC | ALF,       // t
    ALF_LC | ALF,       // u
    ALF_LC | ALF,       // v
    ALF_LC | ALF,       // w
    ALF_LC | ALF,       // x
    ALF_LC | ALF,       // y
    ALF_LC | ALF,       // z
    NULL,               // {    (left/opening brace)
    NULL,               // |    (vertical bar)
    NULL,               // }    (right/closing brace)
    NULL,               // ~    (tilde)
    NULL,               // DEL    (delete)
    NULL                // Padding!    
};

    public static boolean isAll(int ch, int mask) {
        return ((masks[ch & (int)0x0000007F] & mask) == mask);
    }
    
    public static boolean isAny(int ch, int mask) {
        return ((masks[ch & (int)0x0000007F] & mask) != 0);
    }
}
